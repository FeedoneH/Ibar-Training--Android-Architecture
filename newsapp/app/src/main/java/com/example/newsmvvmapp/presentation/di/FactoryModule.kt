package com.example.newsmvvmapp.presentation.di

import android.app.Application
import com.example.newsmvvmapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsmvvmapp.domain.usecase.GetSearchedNewsUseCase
import com.example.newsmvvmapp.presentation.viewmodel.NewsViewModelFactory
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
}