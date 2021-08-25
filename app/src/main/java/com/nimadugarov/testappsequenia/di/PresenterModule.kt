package com.nimadugarov.testappsequenia.di

import com.nimadugarov.testappsequenia.presentation.details.DetailsPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object PresenterModule {

    @Provides
    @FragmentScoped
    fun provideDetailsPresenter(): DetailsPresenter = DetailsPresenter()
}