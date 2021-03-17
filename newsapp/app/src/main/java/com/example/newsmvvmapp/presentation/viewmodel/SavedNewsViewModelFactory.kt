package com.example.newsmvvmapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsmvvmapp.domain.usecase.DeleteSavedNewsUseCase
import com.example.newsmvvmapp.domain.usecase.GetSavedNewsUseCase
import java.lang.IllegalArgumentException

class SavedNewsViewModelFactory(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedNewsViewModel::class.java)) {
            return SavedNewsViewModel(getSavedNewsUseCase,deleteSavedNewsUseCase) as T
        }
        throw IllegalArgumentException("not acceptible argument")
    }
}