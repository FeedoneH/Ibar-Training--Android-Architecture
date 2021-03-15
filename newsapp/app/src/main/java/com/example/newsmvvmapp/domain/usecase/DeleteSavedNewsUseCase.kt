package com.example.newsmvvmapp.domain.usecase

import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute (article: Article) = newsRepository.deleteSavedNews(article)
}