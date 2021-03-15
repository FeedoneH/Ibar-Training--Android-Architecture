package com.example.moviemvvm.data.model.tvShow

import com.example.moviemvvm.data.model.tvShow.TvShow
import com.google.gson.annotations.SerializedName

data class TVShowList(
    @SerializedName("tvShow")
    val tvShow: List<TvShow>,

    )