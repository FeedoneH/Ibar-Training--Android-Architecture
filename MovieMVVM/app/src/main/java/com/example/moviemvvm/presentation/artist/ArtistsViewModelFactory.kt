package com.example.moviemvvm.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvm.domain.usecase.GetArtistsUseCase
import com.example.moviemvvm.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvm.presentation.movie.MovieViewModel
import java.lang.IllegalArgumentException

class ArtistsViewModelFactory(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistsViewModel::class.java)) {
            return ArtistsViewModel(getArtistsUseCase, updateArtistsUseCase) as T
        }
        throw IllegalArgumentException("unacceptable")
    }
}