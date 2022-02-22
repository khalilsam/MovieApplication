package com.example.movieapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.Screen

import com.example.movieapp.data.MovieApi

@ExperimentalMaterialApi
@Composable
fun MainScreen(navController: NavController) {
    val viewModel: MovieViewModel = hiltViewModel()
    val movieCollection = viewModel.state.value.movieCollection
    if (movieCollection!=null) {
        LazyColumn {
            items(movieCollection.results) { movie ->
                val painter = rememberImagePainter(MovieApi.IMAGE_BASE_URL + movie.poster_path)
                Column(modifier = Modifier.padding(16.dp).clickable {
                    navController.navigate(Screen.DetailsScreen.withArg(movie.id))

                }) {
                    ImageCard(
                        painter = painter,
                        contentDescription = movie.title,
                        title = movie.title,
                        date = movie.release_date,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }


            }
        }
    }
}
