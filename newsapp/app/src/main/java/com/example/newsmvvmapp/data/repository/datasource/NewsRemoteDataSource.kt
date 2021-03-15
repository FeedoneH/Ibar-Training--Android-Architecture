package com.example.newsmvvmapp.data.repository.datasource

import com.example.newsmvvmapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNewsHeadlinesFromAPI(country: String, page: Int): Response<APIResponse>
    suspend fun getSearchedNewsHeadlinesFromAPI(country: String, page: Int,searchQuery:String): Response<APIResponse>
}