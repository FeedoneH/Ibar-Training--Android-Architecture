package com.example.newsmvvmapp.domain.usecase

import com.example.newsmvvmapp.data.model.APIResponse
import com.example.newsmvvmapp.data.util.Resource
import com.example.newsmvvmapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String,page:Int):Resource<APIResponse> {
       return newsRepository.getNewsHeadlines(country,page)
    }
}