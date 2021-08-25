package com.nimadugarov.testappsequenia.domain.repository

import com.nimadugarov.testappsequenia.domain.model.MovieItem

interface MovieRepository {

    suspend fun getMovies(): ResponseData<List<MovieItem>>
}