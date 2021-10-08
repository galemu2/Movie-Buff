package com.ctrlaccess.moviebuff.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ctrlaccess.moviebuff.data.MoviesPagingSource
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import com.ctrlaccess.moviebuff.util.Resource
import com.ctrlaccess.moviebuff.util.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.ctrlaccess.moviebuff.data.model.Result


class MoviesRepo @Inject constructor(
    private val moviesApi: MoviesApi
) : RepositoryInterface {

    override suspend fun getCurrentMovies(): Resource<MoviesCurrent> {

        wrapEspressoIdlingResource {
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
    }

    override fun getPopularMovies(): Flow<PagingData<Result>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    MoviesPagingSource(moviesApi)
                }
            ).flow
        }
    }


}