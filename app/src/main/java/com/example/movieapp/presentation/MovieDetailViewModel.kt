package com.example.movieapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.Movie
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Movie details ViewModel
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState
    data class MovieState(val movie: Movie? = null, val isLoading: Boolean = false)
    fun getMovie(id: Int?) {
        viewModelScope.launch {
            try {
                _movieState.value = movieState.value.copy(isLoading = true)
                _movieState.value = movieState.value.copy(
                    movie = repository.getMovie(id),
                    isLoading = false
                )

            } catch (e: Exception) {
                _movieState.value = movieState.value.copy(isLoading = false)
            }
        }
    }
}
