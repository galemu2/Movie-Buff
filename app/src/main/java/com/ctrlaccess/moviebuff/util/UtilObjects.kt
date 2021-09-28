package com.ctrlaccess.moviebuff.util

import com.ctrlaccess.moviebuff.BuildConfig
import com.ctrlaccess.moviebuff.data.model.Result

object UtilObjects {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = BuildConfig.MOVIES_API_KEY

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val IMAGE_SIZE = "w500"

    fun getImageUrl(movie: Result): String {
        return IMAGE_BASE_URL + IMAGE_SIZE + movie.poster_path
    }
}