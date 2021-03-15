package com.example.newsmvvmapp.presentation.di

import com.example.newsmvvmapp.data.repository.NewsRepositoryImpl
import com.example.newsmvvmapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsmvvmapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepositoryModule(newsRemoteDataSource: NewsRemoteDataSource):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource)
    }

}