package com.nimadugarov.testappsequenia.presentation.details

import moxy.MvpPresenter

class DetailsPresenter : MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showMovieDetails()
    }
}