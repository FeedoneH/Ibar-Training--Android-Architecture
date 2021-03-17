package com.example.newsmvvmapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.domain.usecase.DeleteSavedNewsUseCase
import com.example.newsmvvmapp.domain.usecase.GetSavedNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedNewsViewModel(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModel() {
    var savedNewsList: MutableLiveData<List<Article>> = MutableLiveData()


    fun getSavedNews(){
        viewModelScope.launch(Dispatchers.IO) {
            var data = getSavedNewsUseCase.execute()
            savedNewsList.postValue(data)

        }

    }

    fun deleteSavedNews(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSavedNewsUseCase.execute(article)


        }
    }
}