package com.example.movieapp.data

data class MovieCollection(val page:Int,val results: List<Movie>,val total_results:Int,val total_pages:Int)
