package com.ctrlaccess.moviebuff.repo

import androidx.paging.PagingData
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.util.Resource
import com.ctrlaccess.moviebuff.util.Status
import kotlinx.coroutines.flow.Flow

class FakeMoviesRepo : RepositoryInterface {

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
        return if(shouldReturnNetworkError){
            Resource(Status.ERROR,null, "Error")
        }else{
            Resource(Status.SUCCESS, MoviesCurrent(listOf(result)), null)
        }
    }

    override fun getPopularMovies(): Flow<PagingData<Result>>? {
        return null
    }


}
