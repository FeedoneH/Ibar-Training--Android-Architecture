package com.example.moviemvvm.data.model.tvShow


import com.google.gson.annotations.SerializedName

data class TVShowList(
    @SerializedName("results")
    val tvShow: List<TvShow>,

    )