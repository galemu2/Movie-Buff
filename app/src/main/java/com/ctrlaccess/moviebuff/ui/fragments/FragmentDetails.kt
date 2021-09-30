package com.ctrlaccess.moviebuff.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.databinding.FragmentDetailsBinding
import com.ctrlaccess.moviebuff.util.UtilObjects.getImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails constructor(private val glide: RequestManager) : Fragment(R.layout.fragment_details) {

    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val poster = getImageUrl(args.movie)
            glide.load(poster)
                .into(imageViewMovieDetailPoster)


            val title = args.movie.title
            val releaseDate = args.movie.release_date
            val overview = args.movie.overview

            textViewMovieDetailsTitle.text = title
            textViewMovieDetailsReleaseDate.text = releaseDate
            textViewMovieDetailsOverview.text = overview
        }
    }
}