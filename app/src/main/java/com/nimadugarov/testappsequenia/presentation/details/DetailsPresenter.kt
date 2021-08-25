package com.nimadugarov.testappsequenia.presentation.details

import com.nimadugarov.testappsequenia.domain.model.MovieItem
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailsPresenter : MvpPresenter<DetailsView>() {

    var movieItem: MovieItem? = null

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
    }
}