package com.example.movieapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieApi
import com.example.movieapp.data.MovieCollection
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state



    data class MovieListState(
        val movieCollection: MovieCollection? = null,
        val isLoading: Boolean = false
    )



    init {
        getMoviesList()
    }

    fun getMoviesList() {
        viewModelScope.launch {
            try {

                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    movieCollection = repository.getMoviesList(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }


}