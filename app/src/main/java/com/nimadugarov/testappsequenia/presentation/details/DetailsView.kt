package com.nimadugarov.testappsequenia.presentation.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailsView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showMovieDetails()
}