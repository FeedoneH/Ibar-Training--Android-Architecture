package com.example.newsmvvmapp.domain.usecase

import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
     fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}