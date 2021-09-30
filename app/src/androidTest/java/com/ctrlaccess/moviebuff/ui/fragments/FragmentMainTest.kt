package com.ctrlaccess.moviebuff.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import com.ctrlaccess.moviebuff.FakeData
import com.ctrlaccess.moviebuff.launchFragmentInHiltContainer
import com.ctrlaccess.moviebuff.ui.MoviesViewModel
import com.ctrlaccess.moviebuff.ui.TestMovieFragmentFactory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class FragmentMainTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var testFragmentFactory: TestMovieFragmentFactory

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun displayMoviesInView() {
        listOf(FakeData.movies)
        var testViewModel: MoviesViewModel? = null
        launchFragmentInHiltContainer<FragmentMain>(
            fragmentFactory = testFragmentFactory
        ) {
            testViewModel = viewModel
        }
    }
}