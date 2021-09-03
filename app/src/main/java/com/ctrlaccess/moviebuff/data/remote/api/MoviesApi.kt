package com.ctrlaccess.moviebuff.data.remote.api

import com.ctrlaccess.moviebuff.BuildConfig


interface MoviesApi {

    companion object {
        const val BASE_URL=""
        const val API_KEY = BuildConfig.MOVIES_API_KEY
    }
}