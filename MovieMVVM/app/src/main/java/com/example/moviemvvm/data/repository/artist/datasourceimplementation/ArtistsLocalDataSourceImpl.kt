package com.example.moviemvvm.data.repository.artist.datasourceimplementation

import com.example.moviemvvm.data.db.ArtistDAO
import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistsLocalDataSourceImpl(private val artistDAO: ArtistDAO):ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> {
       return artistDAO.selectAllArtists()
    }

    override suspend fun saveArtistsToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDAO.insertArtist(artists)
        }
    }

    override suspend fun clearAllArtistDB() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDAO.deleteAllMovies()
        }
    }
}