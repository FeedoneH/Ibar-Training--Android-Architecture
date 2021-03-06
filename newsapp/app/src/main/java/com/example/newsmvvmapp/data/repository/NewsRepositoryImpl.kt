package com.example.newsmvvmapp.data.repository

import com.example.newsmvvmapp.data.model.APIResponse
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsmvvmapp.data.repository.datasource.SavedNewsLocalDBSource
import com.example.newsmvvmapp.data.util.Resource
import com.example.newsmvvmapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val savedNewsLocalDBSource: SavedNewsLocalDBSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return convertResponseToResource(
            newsRemoteDataSource.getNewsHeadlinesFromAPI(
                country,
                page
            )
        )
    }

    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> {
        return convertResponseToResource(
            newsRemoteDataSource.getSearchedNewsHeadlinesFromAPI(
                country,
                page,
                searchQuery
            )
        )
    }

    fun convertResponseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun deleteSavedNews(article: Article) {
        return savedNewsLocalDBSource.deleteSavedNews(article)
    }

    override suspend fun saveNews(article: Article) {
        return savedNewsLocalDBSource.insertSavedNews(article)
    }

    override suspend fun getSavedNews(): List<Article> {
        return savedNewsLocalDBSource.selectAllSavedNews()
    }
}