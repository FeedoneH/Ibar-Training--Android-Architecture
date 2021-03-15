package com.example.moviemvvm.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviemvvm.domain.usecase.GetTVShowsUseCase
import com.example.moviemvvm.domain.usecase.UpdateTVShowUseCase

class TvShowViewModel(
    private val getTVShowsUseCase: GetTVShowsUseCase,
    private val updateTVShowUseCase: UpdateTVShowUseCase
) : ViewModel() {

    fun getTvShows() = liveData {
        val tvShowList = getTVShowsUseCase.execute()
        emit(tvShowList)
    }

    fun updateTvShows() = liveData {
        val tvShowList = updateTVShowUseCase.execute()
        emit(tvShowList)
    }
}