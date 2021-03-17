package com.example.newsmvvmapp.presentation.viewmodel


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsmvvmapp.domain.usecase.SaveNewsUseCase
import java.lang.IllegalArgumentException

class InfoViewModelFactory(
    private val saveNewsUseCase: SaveNewsUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoViewModel::class.java)) {
            return InfoViewModel(saveNewsUseCase) as T
        }
        throw IllegalArgumentException("not acceptible argument")
    }
}