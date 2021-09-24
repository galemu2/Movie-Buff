package com.ctrlaccess.moviebuff.repo

import androidx.paging.PagingData
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {

    suspend fun getCurrentMovies(): Resource<MoviesCurrent>

    fun getPopularMovies(): Flow<PagingData<Result>>?
}