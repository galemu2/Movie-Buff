package com.ctrlaccess.moviebuff.ui.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class FragmentDetailsTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    /*
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    */

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun activateFragmentDetails_DisplayUi() {
        val movies = Result(
            genre_ids = listOf(1, 2),
            id = 1,
            overview = "overview",
            poster_path = "poster_path",
            release_date = "yesterday",
            title = "best movie",
            vote_average = 2.0f
        )
        val bundle = FragmentDetailsArgs(movies).toBundle()
        launchFragmentInHiltContainer<FragmentDetails>(
            fragmentArgs = bundle,
            themeResId = R.style.Theme_MovieBuff
        ) { }

        onView(withId(R.id.textView_movie_details_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_movie_details_release_date)).check(matches(withText("yesterday")))

    }
}