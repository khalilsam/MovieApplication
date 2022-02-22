package com.example.movieapp

sealed class Screen(val route:String){
    object MainScreen :Screen("MoviesList")
    object DetailsScreen :Screen("MovieDetails")


    fun withArg(vararg args:Int):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")

            }
        }
    }

}
