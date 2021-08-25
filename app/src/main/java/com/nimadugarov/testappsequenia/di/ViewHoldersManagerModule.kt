package com.nimadugarov.testappsequenia.di

import com.nimadugarov.testappsequenia.presentation.home.list.*
import com.nimadugarov.testappsequenia.presentation.home.list.viewholder.GenreItemViewHolder
import com.nimadugarov.testappsequenia.presentation.home.list.viewholder.HeaderItemViewHolder
import com.nimadugarov.testappsequenia.presentation.home.list.viewholder.MovieItemViewHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ViewHoldersManagerModule {

    @Provides
    @FragmentScoped
    fun provideViewHoldersManager(): ViewHoldersManager = ViewHoldersManagerImpl().apply {
        registerViewHolder(ItemTypes.TYPE_HEADER, HeaderItemViewHolder())
        registerViewHolder(ItemTypes.TYPE_GENRE, GenreItemViewHolder())
        registerViewHolder(ItemTypes.TYPE_MOVIE, MovieItemViewHolder())
    }
}