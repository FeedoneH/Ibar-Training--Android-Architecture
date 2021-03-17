package com.example.newsmvvmapp.presentation.di


import android.content.Context
import androidx.room.Room
import com.example.newsmvvmapp.data.db.SavedNewsDB
import com.example.newsmvvmapp.data.db.SavedNewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabaseModule(@ApplicationContext context: Context): SavedNewsDB {
        return Room
            .databaseBuilder(context, SavedNewsDB::class.java, "saved_news")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSavedNewsDao(savedNewsDB: SavedNewsDB):SavedNewsDao{
        return savedNewsDB.savedNewsDao()
    }
}