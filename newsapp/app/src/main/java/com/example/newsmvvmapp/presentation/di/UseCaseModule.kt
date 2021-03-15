package com.example.newsmvvmapp.presentation.di

import android.app.Application
import com.example.newsmvvmapp.domain.repository.NewsRepository
import com.example.newsmvvmapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsmvvmapp.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun getNewsHeadlinesUseCaseModule(newsRepository: NewsRepository): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun getSearchedNewsHeadlinesUseCaseModule(newsRepository: NewsRepository): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }
}