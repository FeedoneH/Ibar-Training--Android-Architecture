package com.example.moviemvvm.domain.repository

import com.example.moviemvvm.data.model.artist.Artist

interface ArtistsRepository {
    suspend fun getArtists():List<Artist>?
    suspend fun updateArtists():List<Artist>?
}