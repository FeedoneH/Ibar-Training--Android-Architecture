package com.example.newsmvvmapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.data.model.Source


@Database(entities = [Article::class], version = 2, exportSchema = false)
abstract class SavedNewsDB : RoomDatabase() {
    abstract fun savedNewsDao(): SavedNewsDao
}