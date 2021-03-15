package com.example.moviemvvm.domain.repository

import com.example.moviemvvm.data.model.tvShow.TvShow

interface TVShowsRepository {
    suspend fun getTVShows():List<TvShow>?
    suspend fun updateTVShows():List<TvShow>?
}