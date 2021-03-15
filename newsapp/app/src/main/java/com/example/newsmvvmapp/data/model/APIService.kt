package com.example.newsmvvmapp.data.model



import com.example.newsmvvmapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("v2/top-headlines")
    suspend fun getNewsHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") api_key: String=BuildConfig.API_KEY
    ):Response<APIResponse>
    @GET("v2/top-headlines")
    suspend fun getSearchedNewsHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("q")searchQuery:String,
        @Query("apiKey") api_key: String=BuildConfig.API_KEY
    ):Response<APIResponse>
}