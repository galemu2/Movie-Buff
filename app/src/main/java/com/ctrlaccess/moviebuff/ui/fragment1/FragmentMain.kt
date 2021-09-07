package com.ctrlaccess.moviebuff.ui.fragment1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMain : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModels<MoviesViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        val adapter = MoviesCurrentAdapter()
        binding.apply {
            recyclerViewMoviesCurrent.setHasFixedSize(true)
            recyclerViewMoviesCurrent.adapter = adapter
//            recyclerViewMoviesCurrent.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.currentMovies.observe(viewLifecycleOwner, Observer {

            adapter.submitMovies(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}