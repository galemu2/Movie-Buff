package com.ctrlaccess.moviebuff

import com.ctrlaccess.moviebuff.data.model.Result

object FakeTestData {

    val movies = Result(
        genre_ids = listOf(1, 2),
        id = 1,
        overview = "overview",
        poster_path = "poster_path",
        release_date = "yesterday",
        title = "best movie",
        vote_average = 2.0f
    )

}