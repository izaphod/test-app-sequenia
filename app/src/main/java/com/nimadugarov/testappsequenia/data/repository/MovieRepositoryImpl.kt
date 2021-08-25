package com.nimadugarov.testappsequenia.data.repository

import com.nimadugarov.testappsequenia.data.network.ApiService
import com.nimadugarov.testappsequenia.data.model.asDomain
import com.nimadugarov.testappsequenia.domain.repository.MovieRepository
import com.nimadugarov.testappsequenia.domain.model.MovieItem
import com.nimadugarov.testappsequenia.domain.repository.ResponseData
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private var apiService: ApiService
) : MovieRepository {

    override suspend fun getMovies(): ResponseData<List<MovieItem>> {
        val response = try {
            apiService.getMovies()
                .body()?.films
                ?.map { it.asDomain() }
        } catch (t: Throwable) {
            return ResponseData.Error(t.message.toString())
        }
        return ResponseData.Success(response!!)
    }
}