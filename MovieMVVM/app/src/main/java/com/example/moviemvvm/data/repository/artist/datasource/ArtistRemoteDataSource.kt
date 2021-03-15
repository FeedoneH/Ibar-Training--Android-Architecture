package com.example.moviemvvm.data.repository.artist.datasource


import com.example.moviemvvm.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtistsFromAPI():Response<ArtistList>
}