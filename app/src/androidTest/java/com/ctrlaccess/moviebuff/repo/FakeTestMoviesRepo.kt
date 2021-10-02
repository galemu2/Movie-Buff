package com.ctrlaccess.moviebuff.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ctrlaccess.moviebuff.FakeData
import com.ctrlaccess.moviebuff.data.MoviesPagingSource
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.data.remote.FakeMoviesAndroidApi
import com.ctrlaccess.moviebuff.util.Resource
import com.ctrlaccess.moviebuff.util.Status
import kotlinx.coroutines.flow.Flow

class FakeTestMoviesRepo : RepositoryInterface {

    val result = Result(
        genre_ids = listOf(1, 2),
        overview = "overview",
        poster_path = "poster_part",
        release_date = "yesterday",
        title = "best movie",
        vote_average = 5.0f
    )

    private var shouldReturnNetworkError = false
    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getCurrentMovies(): Resource<MoviesCurrent> {
        return if (shouldReturnNetworkError) {
            Resource(Status.ERROR, null, "Error")
        } else {
            Resource(Status.SUCCESS, MoviesCurrent(listOf(FakeData.movies, FakeData.movies, FakeData.movies)), null)
        }
    }

    override fun getPopularMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviesPagingSource(FakeMoviesAndroidApi()) }
        ).flow

    }


}
