package com.example.movieapp.di

import com.example.movieapp.data.MovieApi
import com.example.movieapp.data.MovieApi.Companion.API_TOKEN
import com.example.movieapp.data.MovieApi.Companion.BASE_URL
import com.example.movieapp.repository.MovieRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MovieApi
    ) = MovieRepository(api)
    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        //Logging interceptor
         val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
        // Moshi instance
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor = interceptor)
                    .addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter("api_key", API_TOKEN)
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MovieApi::class.java)
    }
}

