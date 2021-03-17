package com.example.newsmvvmapp.presentation.di

import com.example.newsmvvmapp.data.db.SavedNewsDao
import com.example.newsmvvmapp.data.repository.datasource.SavedNewsLocalDBSource
import com.example.newsmvvmapp.data.repository.datasourceimplementation.SavedNewsLocalDBSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataModule(savedNewsDao: SavedNewsDao):SavedNewsLocalDBSource{
        return SavedNewsLocalDBSourceImpl(savedNewsDao)
    }
}