package com.ctrlaccess.moviebuff.data.remote

import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.MoviesPopular
import com.ctrlaccess.moviebuff.util.UtilObjects.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    @GET("movie/now_playing")
    suspend fun getCurrentMovies(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en-US",
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MoviesCurrent>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")
        page: Int,
        @Query("language")
        language: String = "en-US",
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MoviesPopular>
}