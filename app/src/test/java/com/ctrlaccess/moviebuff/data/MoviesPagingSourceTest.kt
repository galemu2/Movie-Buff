package com.ctrlaccess.moviebuff.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.ctrlaccess.moviebuff.FakeTestData
import com.ctrlaccess.moviebuff.MainCoroutineRule
import com.ctrlaccess.moviebuff.data.remote.FakeMoviesApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesPagingSourceTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test paging data`() = runBlocking {

        val pagingSource = MoviesPagingSource(FakeMoviesApi())
        val expected = PagingSource.LoadResult.Page(
            data = listOf(FakeTestData.movies, FakeTestData.movies, FakeTestData.movies, FakeTestData.movies),
            prevKey = null,
            nextKey = 2
        )
        val output = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertThat(expected).isEqualTo(output)
    }
}