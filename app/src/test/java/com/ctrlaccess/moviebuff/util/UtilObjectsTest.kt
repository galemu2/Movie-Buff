package com.ctrlaccess.moviebuff.util

import com.ctrlaccess.moviebuff.data.model.Result
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
            genre_ids = listOf(1, 2),
            overview = "overview",
            poster_path = "poster_part",
            release_date = "yesterday",
            title = "best movie",
            vote_average = 5.0f
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get image url returns a concatenated string`() {
        val actual = getImageUrl(result)
        val expected = IMAGE_BASE_URL + IMAGE_SIZE + result.poster_path
        assertThat(actual).isEqualTo(expected)
    }
}