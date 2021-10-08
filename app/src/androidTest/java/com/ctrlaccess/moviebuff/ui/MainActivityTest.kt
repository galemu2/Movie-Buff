package com.ctrlaccess.moviebuff.ui


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.adapters.MoviesCurrentAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest // instead of @RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun viewMainFragment() = runBlocking {
        // set initial state

        // Start up the activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Espresso code
        onView(withId(R.id.textView_movies_current)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_current)).check(matches(isDisplayed()))

        // RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(0, click())
        onView(withId(R.id.textView_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_popular)).check(matches(isDisplayed()))

        // close the activity
        activityScenario.close()
    }

    @Test
    fun navigateToDetailsFragment_fromCurrentMvoies() = runBlocking {
        // set initial state

        // Start up the activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Espresso code
        onView(withId(R.id.textView_movies_current)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_current)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.textView_movie_details_title)).check(matches(isDisplayed()))

        // close the activity
        activityScenario.close()
    }

    @Test
    fun navigateToDetailsFragment_fromPopularMovies() = runBlocking {
        // set initial state

        // Start up the activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Espresso code
        onView(withId(R.id.textView_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_popular)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.textView_movie_details_title)).check(matches(isDisplayed()))


        // close the activity
        activityScenario.close()
    }
}