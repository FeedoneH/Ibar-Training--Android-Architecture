package com.example.moviemvvm.data.repository.artist.datasourceimplementation

import com.example.moviemvvm.data.api.TMBDService
import com.example.moviemvvm.data.model.artist.ArtistList
import com.example.moviemvvm.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDatSourceImpl(private val tmbdService: TMBDService, private val API_KEY: String) :
    ArtistRemoteDataSource {
    override suspend fun getArtistsFromAPI(): Response<ArtistList> =
        tmbdService.getPopularPeople(API_KEY)
}