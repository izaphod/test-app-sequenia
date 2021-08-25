package com.nimadugarov.testappsequenia.presentation.model

import com.nimadugarov.testappsequenia.domain.model.Genre

data class GenreItemViewModel(
    override val id: String = "genre",
    val genre: Genre,
    var isSelected: Boolean = false
) : ItemViewModel

fun Genre.asGenreItemViewModel(): GenreItemViewModel {
    return GenreItemViewModel(genre = this)
}