package com.example.newsmvvmapp.data.repository.datasource

import com.example.newsmvvmapp.data.model.Article

interface SavedNewsLocalDBSource {
    suspend fun insertSavedNews(article: Article)
    suspend fun selectAllSavedNews():List<Article>
    suspend fun deleteSavedNews(article: Article)
}