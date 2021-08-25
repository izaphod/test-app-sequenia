package com.nimadugarov.testappsequenia.data.model

import com.google.gson.annotations.SerializedName
import com.nimadugarov.testappsequenia.domain.model.Genre
import com.nimadugarov.testappsequenia.domain.model.MovieItem

data class MovieResponse(
    @SerializedName("id") val movieId: Long,
    @SerializedName("localized_name") val localizedName: String,
    @SerializedName("name") val name: String,
    @SerializedName("year") val year: Int,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("image_url") val posterUrl: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("genres") val genreResponseList: List<GenreResponse?>?
)

fun MovieResponse.asDomain(): MovieItem {
    val movieGenres = mutableListOf<Genre>()
    genreResponseList?.map { genreResponse ->
        genreResponse?.asDomain()?.let { genre -> movieGenres.add(genre) }
    }
    return MovieItem(
        movieId = movieId,
        localizedName = localizedName,
        name = name,
        year = year,
        rating = rating,
        posterUrl = posterUrl,
        description = description,
        genres = movieGenres
    )
}

