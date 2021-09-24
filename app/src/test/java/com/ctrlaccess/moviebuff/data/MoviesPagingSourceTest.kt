package com.ctrlaccess.moviebuff.data

import androidx.paging.PagingSource
import com.ctrlaccess.moviebuff.data.remote.FakeMoviesApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesPagingSourceTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `paging source test success`() = runBlockingTest {
        val pagingSource = MoviesPagingSource(FakeMoviesApi())

        val expected = PagingSource.LoadResult.Page(
            data = listOf(FakeMoviesApi.result, FakeMoviesApi.result, FakeMoviesApi.result),
            prevKey = null,
            nextKey = 1
        )

        val output = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertThat(output).isEqualTo(expected)
    }


}