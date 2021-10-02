package com.ctrlaccess.moviebuff.data.remote

import com.ctrlaccess.moviebuff.FakeTestData
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.MoviesPopular
import retrofit2.Response

class FakeMoviesApi : MoviesApi {
    private val movies =
        listOf(FakeTestData.movies, FakeTestData.movies, FakeTestData.movies, FakeTestData.movies)

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