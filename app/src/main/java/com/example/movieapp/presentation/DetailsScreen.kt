package com.example.movieapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.movieapp.data.MovieApi

/**
 * Details screen composable
 */
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(id: Int?) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {
        viewModel.getMovie(id)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        val movie = viewModel.movieState.value.movie
        val painter = rememberImagePainter(MovieApi.IMAGE_BASE_URL + movie?.poster_path)

        if (movie != null) {
            ImageCard(
                painter = painter,
                contentDescription = movie.title,
                title = movie.title,
                date = movie.release_date
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = movie.overview, modifier = Modifier.padding(16.dp))
        }

    }
}
