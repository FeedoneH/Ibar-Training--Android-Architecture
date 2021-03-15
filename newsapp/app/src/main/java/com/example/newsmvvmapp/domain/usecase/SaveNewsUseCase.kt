package com.example.newsmvvmapp.domain.usecase

import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}