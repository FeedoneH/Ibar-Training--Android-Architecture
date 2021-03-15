package com.example.moviemvvm.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvm.domain.usecase.GetTVShowsUseCase
import com.example.moviemvvm.domain.usecase.UpdateTVShowUseCase
import com.example.moviemvvm.presentation.movie.MovieViewModel
import java.lang.IllegalArgumentException

class TvShowViewModelFactory(
    private val getTVShowsUseCase: GetTVShowsUseCase,
    private val updateTVShowUseCase: UpdateTVShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            return TvShowViewModel(getTVShowsUseCase, updateTVShowUseCase) as T
        }
        throw IllegalArgumentException("unacceptable")
    }
}