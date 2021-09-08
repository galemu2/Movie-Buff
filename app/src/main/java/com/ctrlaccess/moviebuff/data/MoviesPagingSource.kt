package com.ctrlaccess.moviebuff.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import javax.inject.Inject


class MoviesPagingSource(val moviesApi: MoviesApi) : PagingSource<Int, Result>() {

    private val STARTING_PAGE = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val pageNumber = params.key ?: 1
            // query the api
            val response = moviesApi.getPopularMovies(page = pageNumber)

            // returned on success
            LoadResult.Page(
                data = response.results,
                prevKey = if (pageNumber == STARTING_PAGE) null else pageNumber - 1,
                nextKey = if (response.results.isNotEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}
