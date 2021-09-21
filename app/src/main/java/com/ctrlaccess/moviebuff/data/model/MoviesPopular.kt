package com.ctrlaccess.moviebuff.data.model

data class MoviesPopular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

