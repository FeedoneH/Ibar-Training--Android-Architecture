package com.example.newsmvvmapp.presentation.viewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.domain.usecase.DeleteSavedNewsUseCase
import com.example.newsmvvmapp.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoViewModel(
    private val saveNewsUseCase: SaveNewsUseCase
) : ViewModel() {

    fun addToSavedNews(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            saveNewsUseCase.execute(article)
        }

    }
}