package com.nimadugarov.testappsequenia.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nimadugarov.testappsequenia.domain.model.Genre
import com.nimadugarov.testappsequenia.domain.repository.MovieRepository
import com.nimadugarov.testappsequenia.domain.repository.ResponseData
import com.nimadugarov.testappsequenia.presentation.model.*
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val repository: MovieRepository
) : MvpPresenter<HomeView>() {

    private var _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    private var movies = mutableListOf<MovieItemViewModel>()
    private var genres = mutableSetOf<GenreItemViewModel>()
    private var defaultItemList = mutableListOf<ItemViewModel>()

    var genresHeader = ""
    var moviesHeader = ""

    private var currentSelectedGenre = -1

    private var presenterJob = Job()
    private val coroutineScope = CoroutineScope(presenterJob + Dispatchers.Main)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterJob.cancel()
    }

    fun onRefreshClicked() {
        loadMovies()
    }

    fun onMovieClicked(movieItemViewModel: MovieItemViewModel) {
        viewState.navigateToDetails(movieItemViewModel)
    }

    fun onGenreClicked(item: GenreItemViewModel, position: Int) {
        when {
            !item.isSelected -> {
                if (currentSelectedGenre > 0) {
                    viewState.unselectGenreItem(currentSelectedGenre)
                }
                viewState.selectGenreItem(position)
                enableFilterByGenre(item.genre)
                currentSelectedGenre = position
            }
            else -> {
                if (position == currentSelectedGenre) {
                    viewState.unselectGenreItem(position)
                    disableFilterByGenre()
                    currentSelectedGenre = -1
                }
            }
        }
    }

    private fun loadMovies() {
        coroutineScope.launch {
            updateState(State.Loading)
            when (val result = repository.getMovies()) {
                is ResponseData.Success -> {
                    result.data?.let { resultData ->
                        val movieList = resultData
                            .map { movieItem ->
                                movieItem.genres?.forEach { genre ->
                                    genre?.let { genres.add(it.asGenreItemViewModel()) }
                                }
                                movieItem.asMovieItemViewModel()
                            }
                            .sortedBy { it.movieItem.localizedName }
                        val itemList = createItemList(movieList)
                        updateView(itemList)
                        updateMovies(movieList)
                        updateState(State.Success)
                        updateDefaultItemList(itemList)
                        logSuccess()
                    }
                }
                else -> {
                    val message = result.message.toString()
                    updateState(State.Error(message))
                    Log.e("HomePresenter", "loadMovies.Error: $message")
                }
            }
        }
    }

    private fun createItemList(movieList: List<MovieItemViewModel>): MutableList<ItemViewModel> {
        viewState.setListHeader()
        return mutableListOf<ItemViewModel>().apply {
            add(HeaderItemViewModel(header = genresHeader))
            addAll(genres)
            add(HeaderItemViewModel(header = moviesHeader))
            addAll(movieList)
        }
    }

    private fun updateView(itemList: List<ItemViewModel>) {
        viewState.submitItems(itemList)
    }

    private fun updateMovies(movieList: List<MovieItemViewModel>) {
        movies.clear()
        movies.addAll(movieList)
    }

    private fun updateState(state: State) {
        _state.value = state
    }

    private fun updateDefaultItemList(itemList: List<ItemViewModel>) {
        defaultItemList.clear()
        defaultItemList.addAll(itemList)
    }

    private fun enableFilterByGenre(genre: Genre) {
        if (movies.isNotEmpty()) {
            val filtered = movies.filter {
                it.movieItem.genres?.contains(genre) == true
            }
            val itemList = createItemList(filtered)
            viewState.submitItems(itemList)
        }
    }

    private fun disableFilterByGenre() {
        viewState.submitItems(defaultItemList)
    }

    private fun logSuccess() {
        Log.d(
            TAG,
            "loadMovies.Success: Movies = ${
                movies.joinToString("\n", "\n") { it.movieItem.name }
            }"
        )
        Log.d(TAG,
            "loadMovies.Success: Genres = ${
                genres.joinToString("\n", "\n") { it.genre.name }
            }")
    }

    companion object {
        private const val TAG = "HomePresenter"
    }
}

sealed class State {
    object Loading : State()
    class Error(val message: String) : State()
    object Success : State()
}