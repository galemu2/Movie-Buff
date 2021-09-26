package com.ctrlaccess.moviebuff

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResourceCompareTest {

    private lateinit var resourceCompare: ResourceCompare

    @Before
    fun setUp() {
        resourceCompare = ResourceCompare()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getResourceName_returnsTrue() {
        val resName = "movieBuff"
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(
            context,
            R.string.app_name,
            resName
        )

        assertThat(result).isTrue()
    }

    @Test
    fun getResourceName_returnsFalse() {
        val resName = "movieBufff"
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(
            context,
            R.string.app_name,
            resName
        )

        assertThat(result).isFalse()
    }
}