package com.ctrlaccess.moviebuff.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,       // 6. List of genres
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,           // 4. Overview
    val popularity: Double,
    val poster_path: String,        // 1. Poster image
    val release_date: String,       // 5. Release date
    val title: String,              // 3. Title
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
) : Parcelable