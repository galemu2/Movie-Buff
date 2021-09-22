package com.ctrlaccess.moviebuff.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ctrlaccess.moviebuff.R
import dagger.hilt.android.AndroidEntryPoint
import com.ctrlaccess.moviebuff.databinding.FragmentDetailsBinding
import com.ctrlaccess.moviebuff.util.UtilObjects.getImageUrl

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {

    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val poster = getImageUrl(args.movie)
            Glide.with(imageViewMovieDetailPoster)
                .load(poster)
                .error(R.drawable.movie_place_holder)
                .centerCrop()
                .into(imageViewMovieDetailPoster)

            val title = args.movie.title ?: " -- "
            val releaseDate = args.movie.release_date ?: " -- "
            val overview = args.movie.overview ?: " -- "

            textViewMovieDetailsTitle.text = title
            textViewMovieDetailsReleaseDate.text = releaseDate
            textViewMovieDetailsOverview.text = overview
        }
    }
}