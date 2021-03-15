package com.example.newsmvvmapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsmvvmapp.data.model.APIResponse
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.data.util.Resource
import com.example.newsmvvmapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsmvvmapp.domain.usecase.GetSearchedNewsUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(
    private val newsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    var newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable(app)) {
                newsHeadlines.postValue(Resource.Loading())
                val apiResult = newsHeadlinesUseCase.execute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(Resource.Error("no internet connetion"))
            }
        } catch (e: Exception) {
            newsHeadlines.postValue(Resource.Error("${e.message.toString()}"))
        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    var searchedList: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    fun getSearchedNewsHeadlines(country: String, page: Int, searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if(isNetworkAvailable(app)){
                    searchedList.postValue(Resource.Loading())
                    var response = searchedNewsUseCase.execute(country,page,searchQuery)
                    searchedList.postValue(response)
                }
            } catch (e:Exception) {
searchedList.postValue(Resource.Error("${e.message.toString()}"))
            }
        }
    }
}