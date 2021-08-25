package com.nimadugarov.testappsequenia.di

import com.nimadugarov.testappsequenia.data.network.ApiService
import com.nimadugarov.testappsequenia.data.repository.MovieRepositoryImpl
import com.nimadugarov.testappsequenia.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService): MovieRepository =
        MovieRepositoryImpl(apiService)
}