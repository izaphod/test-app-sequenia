package com.nimadugarov.testappsequenia.di

import com.nimadugarov.testappsequenia.presentation.details.DetailsPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object PresenterModule {

    @Provides
    @ActivityScoped
    fun provideDetailsPresenter(): DetailsPresenter = DetailsPresenter()
}