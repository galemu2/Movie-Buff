package com.ctrlaccess.moviebuff.ui


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.adapters.MoviesCurrentAdapter
import com.ctrlaccess.moviebuff.util.DataBindingIdlingResource
import com.ctrlaccess.moviebuff.util.EspressoIdlingResource
import com.ctrlaccess.moviebuff.util.monitorActivity
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

    // idling resource that waits for data binding (no pending bindings)
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)


    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)

        // close the activity
        activityScenario.close()
    }

    @Test
    fun viewMainFragment(): Unit = runBlocking {
        // set initial state

        // Start up the activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Espresso code
        onView(withId(R.id.textView_movies_current)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_current)).check(matches(isDisplayed()))

        // RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(0, click())
        onView(withId(R.id.textView_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_popular)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailsFragment_fromCurrentMovies(): Unit = runBlocking {
        // set initial state

        // Start up the activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Espresso code
        onView(withId(R.id.textView_movies_current)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_current)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.textView_movie_details_title)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailsFragment_fromCurrentMovies_backToMain(): Unit = runBlocking {
        // set initial state

        // Start up the activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Espresso code
        onView(withId(R.id.textView_movies_current)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_current)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.textView_movie_details_title)).check(matches(isDisplayed()))

        pressBack()
        onView(withId(R.id.textView_movies_current)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_current)).check(matches(isDisplayed()))
    }


    @Test
    fun navigateToDetailsFragment_fromPopularMovies(): Unit = runBlocking {
        // set initial state

        // Start up the activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Espresso code
        onView(withId(R.id.textView_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_popular)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.textView_movie_details_title)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailsFragment_fromPopularMovies_backToMain(): Unit = runBlocking {
        // set initial state

        // Start up the activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Espresso code
        onView(withId(R.id.textView_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_popular)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesCurrentAdapter.CurrentViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.textView_movie_details_title)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.textView_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_popular)).check(matches(isDisplayed()))
    }

}