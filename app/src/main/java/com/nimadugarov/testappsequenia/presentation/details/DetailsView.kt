package com.nimadugarov.testappsequenia.presentation.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailsView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setToolbarTitle(title: String)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setMovieDetails(name: String, year: String, rating: Double?, description: String?)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setMoviePoster(posterUrl: String?)
}