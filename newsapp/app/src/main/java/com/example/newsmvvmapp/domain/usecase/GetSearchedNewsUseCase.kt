package com.example.newsmvvmapp.domain.usecase

import com.example.newsmvvmapp.data.model.APIResponse
import com.example.newsmvvmapp.data.util.Resource
import com.example.newsmvvmapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String, page:Int,searchQuery:String):Resource<APIResponse>{
        return newsRepository.getSearchedNews(country,page,searchQuery)
    }
}