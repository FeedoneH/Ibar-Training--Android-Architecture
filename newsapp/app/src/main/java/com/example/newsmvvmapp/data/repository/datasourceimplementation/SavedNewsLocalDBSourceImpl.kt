package com.example.newsmvvmapp.data.repository.datasourceimplementation

import com.example.newsmvvmapp.data.db.SavedNewsDao
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.data.repository.datasource.SavedNewsLocalDBSource

class SavedNewsLocalDBSourceImpl(private val savedNewsDao: SavedNewsDao) : SavedNewsLocalDBSource {
    override suspend fun insertSavedNews(article: Article) {
        return savedNewsDao.insert(article)
    }

    override suspend fun selectAllSavedNews(): List<Article> {
        return savedNewsDao.select()
    }

    override suspend fun deleteSavedNews(article: Article) {
        return savedNewsDao.delete(article)
    }
}