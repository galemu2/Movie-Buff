package com.ctrlaccess.moviebuff.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ctrlaccess.moviebuff.MainCoroutineRule
import com.ctrlaccess.moviebuff.getOrAwaitValueTest
import com.ctrlaccess.moviebuff.repo.FakeMoviesRepo
import com.ctrlaccess.moviebuff.repo.MoviesRepoInterface
import com.ctrlaccess.moviebuff.util.Resource
import com.ctrlaccess.moviebuff.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MoviesViewModel
    private lateinit var repo: MoviesRepoInterface
    @Before
    fun setUp() {
        repo = FakeMoviesRepo()
        viewModel = MoviesViewModel(repo)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get current movies success`() {
        viewModel.getCurrentMovies()
        val value = viewModel.currentMovies.getOrAwaitValueTest()
        val status = value.getContentIfNotHandled()?.status ?: Resource.error("some error", null)
        assertThat(status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `get current movies network error`(){


    }
}