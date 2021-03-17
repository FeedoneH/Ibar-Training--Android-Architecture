package com.example.newsmvvmapp.data.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "saved_news_source_table")
data class Source(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)