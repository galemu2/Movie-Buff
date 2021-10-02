package com.ctrlaccess.moviebuff.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.ctrlaccess.moviebuff.FakeData
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.launchFragmentInHiltContainer
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
class FragmentDetailsTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestMovieFragmentFactory

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun activateFragmentDetails_DisplayUi() {
        val movies = FakeData.movies
        val bundle = FragmentDetailsArgs(movies).toBundle()
        launchFragmentInHiltContainer<FragmentDetails>(
            fragmentArgs = bundle,
           /* themeResId = R.style.Theme_MovieBuff,*/
            fragmentFactory = testFragmentFactory
        )

        onView(withId(R.id.textView_movie_details_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_movie_details_release_date)).check(matches(withText("yesterday")))
    }

}