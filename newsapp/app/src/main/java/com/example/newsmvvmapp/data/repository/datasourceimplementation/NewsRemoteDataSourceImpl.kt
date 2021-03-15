package com.example.newsmvvmapp.data.repository.datasourceimplementation

import com.example.newsmvvmapp.data.model.APIResponse
import com.example.newsmvvmapp.data.model.APIService
import com.example.newsmvvmapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val newsAPIService: APIService): NewsRemoteDataSource {
    override suspend fun getNewsHeadlinesFromAPI(country:String, page:Int): Response<APIResponse> {
       return newsAPIService.getNewsHeadLines(country,page)
    }

    override suspend fun getSearchedNewsHeadlinesFromAPI(
        country: String,
        page: Int,
        searchQuery: String
    ): Response<APIResponse> {
        return newsAPIService.getSearchedNewsHeadLines(country,page,searchQuery)
    }
}