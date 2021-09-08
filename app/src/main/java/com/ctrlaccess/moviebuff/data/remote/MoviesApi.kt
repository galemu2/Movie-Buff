package com.ctrlaccess.moviebuff.data.remote

import com.ctrlaccess.moviebuff.data.MoviesCurrent
import com.ctrlaccess.moviebuff.data.MoviesPopular
import com.ctrlaccess.moviebuff.util.UtilObjects.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    companion object {
        const val MOVIES_CURRENT = "/movie/now_playing"
        const val LANGUAGE_PAGE = "language=en-US&page=undefined&"


        // todo later
        const val MOVIES_POPULAR = "/movie/popular?"

    }

    @GET("movie/now_playing")
    suspend fun getCurrentMovies(
        @Query("language")
        language: String = "en-US",
        @Query("api_key")
        apiKey: String = API_KEY
    ): MoviesCurrent

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")
        page: Int,
        @Query("language")
        language: String = "en-US",

        @Query("api_key")
        apiKey: String = API_KEY
    ): MoviesPopular
}