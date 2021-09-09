package com.ctrlaccess.moviebuff.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ctrlaccess.moviebuff.data.MoviesPagingSource
import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import javax.inject.Inject

class MoviesRepo @Inject constructor(private val moviesApi: MoviesApi) {

    suspend fun getCurrentMovies() = moviesApi.getCurrentMovies()

    fun getPopularMovies() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            MoviesPagingSource(moviesApi)
        }
    ).flow
}