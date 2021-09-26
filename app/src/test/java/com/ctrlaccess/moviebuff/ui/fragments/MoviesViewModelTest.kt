package com.ctrlaccess.moviebuff.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ctrlaccess.moviebuff.MainCoroutineRule
import com.ctrlaccess.moviebuff.getOrAwaitValueTest
import com.ctrlaccess.moviebuff.repo.FakeMoviesRepo
import com.ctrlaccess.moviebuff.ui.MoviesViewModel
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

    // @ExperimentalCoroutinesApi
    // val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MoviesViewModel
    private lateinit var repo: FakeMoviesRepo

    @Before
    fun setUp() {
        repo = FakeMoviesRepo()
        viewModel = MoviesViewModel(repo)
        // Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Dispatchers.resetMain()
        // testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get current movies success`() {
        viewModel.getCurrentMovies()
        val value = viewModel.currentMovies.getOrAwaitValueTest()
        val status = value.getContentIfNotHandled()?.status ?: Status.ERROR
        assertThat(status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `get current movies network error`() {
        repo.setShouldReturnNetworkError(true)
        viewModel.getCurrentMovies()
        val value = viewModel.currentMovies.getOrAwaitValueTest()
        val status = value.getContentIfNotHandled()?.status ?: Status.SUCCESS
        assertThat(status).isEqualTo(Status.ERROR)
    }


    //TODO having difficulty testing for 'Flow<PagingData<Result>>' response
    @Test
    fun `get popular movies success`(){

    }

    @Test
    fun `get popular movies error`(){

    }

    @Test
    fun `movies Current returns error`(){
        viewModel.movieCurrentError(true)
        val output  = viewModel.movieCurrentError.getOrAwaitValueTest()
        assertThat(output).isTrue()
    }

    @Test
    fun `movies Current returns success`(){
        viewModel.movieCurrentError(false)
        val output = viewModel.movieCurrentError.getOrAwaitValueTest()
        assertThat(output).isFalse()
    }

}