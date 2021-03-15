package com.example.newsmvvmapp.presentation.di

import com.example.newsmvvmapp.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideAdapter():NewsAdapter{
        return NewsAdapter()
    }
}