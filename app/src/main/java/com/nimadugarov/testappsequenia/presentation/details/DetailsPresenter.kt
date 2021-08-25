package com.nimadugarov.testappsequenia.presentation.details

import android.util.Log
import com.nimadugarov.testappsequenia.domain.model.MovieItem
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailsPresenter(var movieItem: MovieItem?) : MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        movieItem?.let {
            viewState.setToolbarTitle(it.localizedName)
            viewState.setMoviePoster(it.posterUrl)
            viewState.setMovieDetails(
                name = it.name,
                year = it.year.toString(),
                rating = it.rating,
                description = it.description
            )
        }

        Log.d(TAG, "onFirstViewAttach: ${movieItem?.name}")
    }

    companion object {
        private const val TAG = "DetailsPresenter"
    }
}