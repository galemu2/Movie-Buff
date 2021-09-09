package com.ctrlaccess.moviebuff.ui.fragment1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.adapters.MoviesCurrentAdapter
import com.ctrlaccess.moviebuff.adapters.MoviesLoadStateAdapter
import com.ctrlaccess.moviebuff.adapters.MoviesPopularAdaptor
import com.ctrlaccess.moviebuff.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentMain : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModels<MoviesViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private lateinit var moviesCurrentAdapter: MoviesCurrentAdapter
    private lateinit var moviesPopularAdaptor: MoviesPopularAdaptor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        moviesCurrentAdapter = setupCurrentMoviesRecyclerView()
        moviesPopularAdaptor = setupPopularMovesAdapter() // paging adapter

        viewModel.currentMovies.observe(viewLifecycleOwner, Observer {
            moviesCurrentAdapter.submitCurrentMovies(it)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.popularMovies.collectLatest { pagingData ->
                moviesPopularAdaptor.submitData(pagingData)
            }
        }
    }

    // setup paging adapter
    private fun setupPopularMovesAdapter(): MoviesPopularAdaptor {
        val adaptor = MoviesPopularAdaptor()
        binding.apply {
            recyclerViewMoviesPopular.setHasFixedSize(true)
            recyclerViewMoviesPopular.adapter = adaptor.withLoadStateHeaderAndFooter(
                header = MoviesLoadStateAdapter { adaptor.retry() },
                footer = MoviesLoadStateAdapter { adaptor.retry() }
            )

        }
        return adaptor
    }

    private fun setupCurrentMoviesRecyclerView(): MoviesCurrentAdapter {
        val adapter = MoviesCurrentAdapter()
        binding.apply {
            recyclerViewMoviesCurrent.setHasFixedSize(true)
            recyclerViewMoviesCurrent.adapter = adapter
        }
        return adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}