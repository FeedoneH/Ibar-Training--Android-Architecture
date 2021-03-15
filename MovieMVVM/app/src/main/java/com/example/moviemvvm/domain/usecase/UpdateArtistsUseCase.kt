package com.example.moviemvvm.domain.usecase

import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.domain.repository.ArtistsRepository

class UpdateArtistsUseCase(private val artistsRepository: ArtistsRepository) {
suspend fun execute():List<Artist>?=artistsRepository.updateArtists()
}