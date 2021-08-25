package com.nimadugarov.testappsequenia.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponseList(
    @SerializedName("films") val films: List<MovieResponse>
)
