package com.nimadugarov.testappsequenia.presentation.model

import com.nimadugarov.testappsequenia.domain.model.MovieItem

data class MovieItemViewModel(
    override val id: String = "movie",
    val movieItem: MovieItem
) : ItemViewModel

fun MovieItem.asMovieItemViewModel(): MovieItemViewModel = MovieItemViewModel(movieItem = this)
