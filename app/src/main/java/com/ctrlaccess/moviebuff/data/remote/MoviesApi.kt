package com.ctrlaccess.moviebuff.data.remote

import com.ctrlaccess.moviebuff.BuildConfig
import com.ctrlaccess.moviebuff.data.MoviesCurrent
import com.ctrlaccess.moviebuff.data.Result
import com.ctrlaccess.moviebuff.util.Constants.API_KEY
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
    ):MoviesCurrent


}