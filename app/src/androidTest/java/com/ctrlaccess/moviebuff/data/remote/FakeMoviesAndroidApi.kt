package com.ctrlaccess.moviebuff.data.remote

import com.ctrlaccess.moviebuff.FakeData
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.MoviesPopular
import retrofit2.Response

class FakeMoviesAndroidApi : MoviesApi {
    private val movies = listOf(FakeData.movies, FakeData.movies, FakeData.movies, FakeData.movies)
    override suspend fun getCurrentMovies(
        page: Int,
        language: String,
        apiKey: String
    ): Response<MoviesCurrent> {
        val moviesCurrent = MoviesCurrent(movies)
        return Response.success(moviesCurrent)
    }

    override suspend fun getPopularMovies(
        page: Int,
        language: String,
        apiKey: String
    ): Response<MoviesPopular> {
        val moviesPopular = MoviesPopular(movies)
        return Response.success(moviesPopular)
    }
}