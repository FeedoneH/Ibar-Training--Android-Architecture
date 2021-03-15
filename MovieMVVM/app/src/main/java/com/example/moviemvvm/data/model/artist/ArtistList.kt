package com.example.moviemvvm.data.model.artist


import com.example.moviemvvm.data.model.artist.Artist
import com.google.gson.annotations.SerializedName


data class ArtistList(

    @SerializedName("results")
    val artists: List<Artist>,
)