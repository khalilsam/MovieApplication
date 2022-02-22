package com.example.movieapp.repository

import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieApi
import com.example.movieapp.data.MovieCollection
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class MovieRepository  @Inject constructor(
private val api:MovieApi
){
  suspend fun getMoviesList():MovieCollection{
      return api.getMovieList()
  }

    suspend fun getMovie(id:Int?):Movie{
        return api.getMovie(id)
    }
}