package com.nimadugarov.testappsequenia.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
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

