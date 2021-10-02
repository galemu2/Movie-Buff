package com.ctrlaccess.moviebuff.ui.fragments


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.ctrlaccess.moviebuff.FakeData
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.adapters.MoviesCurrentAdapter
import com.ctrlaccess.moviebuff.adapters.MoviesPopularAdaptor
import com.ctrlaccess.moviebuff.launchFragmentInHiltContainer
import com.ctrlaccess.moviebuff.ui.TestMovieFragmentFactory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
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
    fun displayMoviesInView_testNavigateToCurrentMovieDetails() {
        val navController1 = mock(NavController::class.java)
        launchFragmentInHiltContainer<FragmentMain>(
            fragmentFactory = testFragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController1)
        }

        onView(withId(R.id.recyclerView_movies_current)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0, click()
            )
        )

        verify(navController1).navigate(
            FragmentMainDirections.actionFragmentMainToFragmentDetails2(
                FakeData.movies
            )
        )
    }

    @Test
    fun displayMoviesInView_testNavigateToPopularMovieDetails() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<FragmentMain>(
            fragmentFactory = testFragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.recyclerView_movies_popular)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesPopularAdaptor.MoviesViewHolder>(
                0, click()
            )
        )

        verify(navController).navigate(
            FragmentMainDirections.actionFragmentMainToFragmentDetails2(
                FakeData.movies
            )
        )
    }
}