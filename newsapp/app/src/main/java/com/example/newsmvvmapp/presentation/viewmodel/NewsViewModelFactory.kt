package com.example.newsmvvmapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsmvvmapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsmvvmapp.domain.usecase.GetSearchedNewsUseCase
import java.lang.IllegalArgumentException

class NewsViewModelFactory(
    private val newsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(NewsViewModel::class.java)) {
           return NewsViewModel(newsHeadlinesUseCase,searchedNewsUseCase,app) as T
       }
        throw IllegalArgumentException("not acceptible argument")
    }
}