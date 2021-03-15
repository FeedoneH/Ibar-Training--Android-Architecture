package com.example.newsmvvmapp.domain.repository

import com.example.newsmvvmapp.data.model.APIResponse
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country:String,page:Int):Resource<APIResponse>
    suspend fun getSearchedNews(country: String,page: Int,searchQuery:String):Resource<APIResponse>
    suspend fun deleteSavedNews(article: Article)
    suspend fun saveNews(article: Article)
    fun getSavedNews():Flow<List<Article>>
}