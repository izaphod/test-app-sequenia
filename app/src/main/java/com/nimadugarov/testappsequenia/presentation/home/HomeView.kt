package com.nimadugarov.testappsequenia.presentation.home

import com.nimadugarov.testappsequenia.presentation.model.ItemViewModel
import com.nimadugarov.testappsequenia.presentation.model.MovieItemViewModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface HomeView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun submitItems(items: List<ItemViewModel>)

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun selectGenreItem(position: Int)

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun unselectGenreItem(position: Int)

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun navigateToDetails(movieItemViewModel: MovieItemViewModel)
}