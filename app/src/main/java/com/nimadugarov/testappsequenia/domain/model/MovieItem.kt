package com.nimadugarov.testappsequenia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val movieId: Long,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double?,
    val posterUrl: String?,
    val description: String?,
    val genres: List<Genre?>?
) : Parcelable

