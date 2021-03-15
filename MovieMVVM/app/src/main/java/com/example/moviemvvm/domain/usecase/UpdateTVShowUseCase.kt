package com.example.moviemvvm.domain.usecase

import com.example.moviemvvm.data.model.tvShow.TvShow
import com.example.moviemvvm.domain.repository.TVShowsRepository

class UpdateTVShowUseCase(private val tvShowsRepository: TVShowsRepository) {
    suspend fun execute():List<TvShow>?=tvShowsRepository.updateTVShows()
}