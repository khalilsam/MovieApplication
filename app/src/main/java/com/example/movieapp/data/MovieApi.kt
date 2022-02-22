package com.example.movieapp.data

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit movie api
 */
interface MovieApi {

    // get movie list
    @GET("discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc")
    suspend fun getMovieList(): MovieCollection

    // get movie by id
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movie_id: Int?): Movie

    /**
     * constants
     */
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

        const val API_TOKEN = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    }
}