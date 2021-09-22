package com.ctrlaccess.moviebuff.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(

    val genre_ids: List<Int>,       // 6. List of genres
    var id: Int? = null,
    val overview: String,           // 4. Overview
    val poster_path: String,        // 1. Poster image
    val release_date: String,       // 5. Release date
    val title: String,              // 3. Title
    val vote_average: Float         // ?. Rating

) : Parcelable