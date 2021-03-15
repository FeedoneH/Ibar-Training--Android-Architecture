package com.example.newsmvvmapp.presentation.di

import com.example.newsmvvmapp.data.model.APIService
import com.example.newsmvvmapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsmvvmapp.data.repository.datasourceimplementation.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideRemoteDataModule(newsAPIService: APIService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}