package com.example.moviemvvm.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviemvvm.domain.usecase.GetArtistsUseCase
import com.example.moviemvvm.domain.usecase.UpdateArtistsUseCase

class ArtistsViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {

    fun getArtists() = liveData {
        val artistsList = getArtistsUseCase.execute()
        emit(artistsList)
    }

    fun updateArtists()= liveData{
        val artistsList = updateArtistsUseCase.execute()
        emit(artistsList)
    }
}