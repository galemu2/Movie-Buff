package com.ctrlaccess.moviebuff.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ctrlaccess.moviebuff.data.MoviesPagingSource
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import com.ctrlaccess.moviebuff.util.Resource
import javax.inject.Inject

class MoviesRepo @Inject constructor(private val moviesApi: MoviesApi) : MoviesRepoInterface {

    // suspend fun getCurrentMovies() = moviesApi.getCurrentMovies()

    override suspend fun getCurrentMovies(): Resource<MoviesCurrent> {
        return try {
            val response = moviesApi.getCurrentMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: return Resource.error("Unknown error", null)
            } else {
                return Resource.error("Unknown error", null)
            }
        } catch (e: Exception) {
            return Resource.error("Could not reach the server. Check internet connection", null)
        }
    }


    override fun getPopularMovies() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            MoviesPagingSource(moviesApi)
        }
    ).flow
}