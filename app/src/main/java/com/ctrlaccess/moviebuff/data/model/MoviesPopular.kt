package com.ctrlaccess.moviebuff.data.model

import com.ctrlaccess.moviebuff.data.model.Result

data class MoviesPopular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

