package com.example.movieapp.data

import com.squareup.moshi.Json


data class Movie(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name="poster_path")
    val poster_path:String,
    @Json(name="release_date")
    val release_date:String
)
