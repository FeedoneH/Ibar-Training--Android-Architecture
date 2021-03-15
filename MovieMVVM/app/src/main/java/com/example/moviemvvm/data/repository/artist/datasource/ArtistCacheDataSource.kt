package com.example.moviemvvm.data.repository.artist.datasource

import com.example.moviemvvm.data.model.artist.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache():List<Artist>
    suspend fun saveArtistsToCache(artist:List<Artist>)
}