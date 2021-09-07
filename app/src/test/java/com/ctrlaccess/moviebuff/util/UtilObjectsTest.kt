package com.ctrlaccess.moviebuff.util

import com.ctrlaccess.moviebuff.data.Result
import com.ctrlaccess.moviebuff.util.UtilObjects.IMAGE_BASE_URL
import com.ctrlaccess.moviebuff.util.UtilObjects.IMAGE_SIZE
import com.ctrlaccess.moviebuff.util.UtilObjects.getImageUrl
import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test

class UtilObjectsTest {

    private lateinit var result: Result

    @Before
    fun setUp() {
        result = Result(
            false, "", emptyList(), 1, "en-US", "Hot Movie",
            "Very hot movie", 4.4, "poster_path", "09/07/2021", "Hot Movie", false,
            4.4f, 10000
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get image url returns a concatenated string`() {
        val out = getImageUrl(result)
        val compare = IMAGE_BASE_URL + IMAGE_SIZE + result.poster_path
        assertThat(out).isEqualTo(compare)
    }
}