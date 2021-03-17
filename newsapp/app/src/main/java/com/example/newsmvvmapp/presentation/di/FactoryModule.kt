package com.example.newsmvvmapp.presentation.di

import android.app.Application
import com.example.newsmvvmapp.domain.usecase.*
import com.example.newsmvvmapp.presentation.viewmodel.InfoViewModelFactory
import com.example.newsmvvmapp.presentation.viewmodel.NewsViewModelFactory
import com.example.newsmvvmapp.presentation.viewmodel.SavedNewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        newsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        app: Application
    ): NewsViewModelFactory {
        return NewsViewModelFactory(newsHeadlinesUseCase,getSearchedNewsUseCase, app)
    }
    @Singleton
    @Provides
    fun provideInfoViewModelFactory(
       saveNewsUseCase: SaveNewsUseCase
    ): InfoViewModelFactory {
        return InfoViewModelFactory(saveNewsUseCase)
    }
    @Singleton
    @Provides
    fun provideSavedNewsViewModelFactory(
       getSavedNewsUseCase: GetSavedNewsUseCase,
       deleteSavedNewsUseCase: DeleteSavedNewsUseCase
    ): SavedNewsViewModelFactory {
        return SavedNewsViewModelFactory(getSavedNewsUseCase,deleteSavedNewsUseCase)
    }
}