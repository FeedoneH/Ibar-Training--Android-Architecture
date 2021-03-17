package com.example.newsmvvmapp.data.db

import androidx.room.*
import com.example.newsmvvmapp.data.model.Article


@Dao
interface SavedNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("Select*from saved_news_table")
    suspend fun select(): List<Article>

    @Delete
    suspend fun delete(article: Article)

}