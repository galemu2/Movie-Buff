package com.ctrlaccess.moviebuff.repositories

import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import javax.inject.Inject

class MoviesRepo @Inject constructor( private val moviesApi: MoviesApi){

    suspend fun getCurrentMovies() = moviesApi.getCurrentMovies()

}