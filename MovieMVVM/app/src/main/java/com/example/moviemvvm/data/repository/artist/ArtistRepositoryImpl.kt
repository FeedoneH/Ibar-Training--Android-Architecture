package com.example.moviemvvm.data.repository.artist

import android.util.Log
import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.moviemvvm.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.moviemvvm.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.moviemvvm.domain.repository.ArtistsRepository
import java.lang.Exception

class ArtistRepositoryImpl(
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource,
    private val artistRemoteDataSource: ArtistRemoteDataSource
) : ArtistsRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
      lateinit var updatedArtistList:List<Artist>
      updatedArtistList=getArtistsFromAPI()
        artistLocalDataSource.clearAllArtistDB()
        artistLocalDataSource.saveArtistsToDB(updatedArtistList)
        artistCacheDataSource.saveArtistsToCache(updatedArtistList)
        return updatedArtistList

    }

    suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            artistsList = artistLocalDataSource.getArtistsFromDB()

        } catch (e: Exception) {
            Log.i("errror", "$e")
        }
        if (artistsList.size > 0) {
            return artistsList
        } else {
            artistsList = getArtistsFromAPI()
        }
        return artistsList

    }

    suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artistsList: List<Artist>
        var response = artistRemoteDataSource.getArtistsFromAPI()
        var responseValue = response.body()
        try {
            if (responseValue != null) {
                artistsList = responseValue.artists

            }
        } catch (e: Exception) {
            Log.i("errror", "$e")
        }
        return artistsList
    }

    suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            artistsList=artistCacheDataSource.getArtistsFromCache()
            if(artistsList.size>0){
                return artistsList
            }
            else{
                artistsList=getArtistsFromDB()
                artistCacheDataSource.saveArtistsToCache(artistsList)

            }
        }catch (e: Exception) {
            Log.i("errror", "$e")
        }

        return artistsList
    }
}