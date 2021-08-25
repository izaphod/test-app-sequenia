package com.nimadugarov.testappsequenia.presentation.model

data class HeaderItemViewModel(
    override val id: String = "header",
    val header: String
) : ItemViewModel
