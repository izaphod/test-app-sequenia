package com.nimadugarov.testappsequenia.data.network

import com.nimadugarov.testappsequenia.data.model.MovieResponseList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("films.json")
    suspend fun getMovies(): Response<MovieResponseList>
}