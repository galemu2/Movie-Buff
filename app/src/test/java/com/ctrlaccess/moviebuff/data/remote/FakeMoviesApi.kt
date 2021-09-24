package com.ctrlaccess.moviebuff.data.remote

import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.MoviesPopular
import com.ctrlaccess.moviebuff.data.model.Result
import retrofit2.Response

class FakeMoviesApi : MoviesApi {

    companion object {

        val result: Result = Result(
            genre_ids = listOf(1, 2),
            id = null,
            overview = "overview",
            poster_path = "path",
            release_date = "yesterday",
            title = "title",
            vote_average = 5.0f
        )

    }

    private val moviesCurrent: MoviesCurrent = MoviesCurrent(results = listOf(result))

    // popular movies are packaged in pagingSource
    private val moviesPopular: MoviesPopular =
        MoviesPopular(results = listOf(result, result, result))

    override suspend fun getCurrentMovies(
        language: String,
        apiKey: String
    ): Response<MoviesCurrent> {
        return Response.success(moviesCurrent)
    }

    // handled by paging
    override suspend fun getPopularMovies(
        page: Int,
        language: String,
        apiKey: String
    ): MoviesPopular {
        return moviesPopular
    }
}