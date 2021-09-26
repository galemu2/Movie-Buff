package com.ctrlaccess.moviebuff.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.adapters.MoviesCurrentAdapter
import com.ctrlaccess.moviebuff.adapters.MoviesLoadStateAdapter
import com.ctrlaccess.moviebuff.adapters.MoviesPopularAdaptor
import com.ctrlaccess.moviebuff.databinding.FragmentMainBinding
import com.ctrlaccess.moviebuff.ui.MoviesViewModel
import com.ctrlaccess.moviebuff.util.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentMain : Fragment(R.layout.fragment_main) {

    private val TAG = "FragmentMain"
    private val viewModel by viewModels<MoviesViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private lateinit var moviesCurrentAdapter: MoviesCurrentAdapter
    private lateinit var moviesPopularAdaptor: MoviesPopularAdaptor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        moviesCurrentAdapter = setupCurrentMoviesAdapter()
        moviesCurrentAdapter.setOnItemClickListener { result ->
            val action = FragmentMainDirections.actionFragmentMainToFragmentDetails2(result)
            findNavController().navigate(action)
        }
        moviesPopularAdaptor = setupPopularMovesAdapter() // paging adapter
        moviesPopularAdaptor.setOnItemClickListener { result ->
            val action = FragmentMainDirections.actionFragmentMainToFragmentDetails2(result)
            findNavController().navigate(action)
        }
        viewModel.getCurrentMovies()
        observeCurrentMovies()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.popularMovies?.collectLatest { pagingData ->
                moviesPopularAdaptor.submitData(pagingData)
            }
        }

        popularMoviesLoadState()

        binding.fabUiNotLoading.setOnClickListener {
            viewModel.getCurrentMovies()
            moviesPopularAdaptor.retry()
        }

        ovserveErrors()
    }

    private fun popularMoviesLoadState() {
        moviesPopularAdaptor.addLoadStateListener { loadState ->
            binding.apply {
                container1.isVisible = true
                val currentState = loadState.source.refresh

                viewModel.moviePopularError(currentState is LoadState.Error)
                when (currentState) {
                    is LoadState.Loading -> {

                        progressBarMoviesPopular.visibility = View.VISIBLE
                        textViewMoviesPopular.visibility = View.GONE
                        recyclerViewMoviesPopular.visibility = View.GONE

                        viewModel.moviePopularError(false)
                    }
                    is LoadState.NotLoading -> { // success
                        container1.visibility = View.VISIBLE
                        textViewMoviesPopular.visibility = View.VISIBLE
                        recyclerViewMoviesPopular.visibility = View.VISIBLE
                        progressBarMoviesPopular.visibility = View.GONE
                        viewModel.moviePopularError(false)

                    }
                    is LoadState.Error -> {
                        container1.visibility = View.GONE
                        progressBarMoviesPopular.visibility = View.GONE
                        viewModel.moviePopularError(true)
                    }
                }

            }
        }
    }

    private fun ovserveErrors() {
        viewModel.moviePopularError.observe(viewLifecycleOwner, Observer { popularMovieError ->
            val currentMovieError = viewModel.movieCurrentError.value ?: false
            binding.fabUiNotLoading.isVisible = popularMovieError && currentMovieError
        })

        viewModel.movieCurrentError.observe(viewLifecycleOwner, Observer { currentMovieError ->
            val popularMovieError = viewModel.moviePopularError.value ?: false
            binding.fabUiNotLoading.isVisible = popularMovieError && currentMovieError
        })
    }

    private fun observeCurrentMovies() {
        viewModel.currentMovies.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        binding.textViewMoviesCurrent.visibility = View.VISIBLE
                        binding.progressBarMoviesCurrent.visibility = View.GONE
                        moviesCurrentAdapter.submitCurrentMovies(result.data?.results)
                        viewModel.movieCurrentError(false)
                    }
                    Status.LOADING -> {
                        binding.textViewMoviesCurrent.visibility = View.GONE
                        binding.progressBarMoviesCurrent.visibility = View.VISIBLE
                        viewModel.movieCurrentError(false)
                    }
                    Status.ERROR -> {
                        binding.textViewMoviesCurrent.visibility = View.GONE
                        binding.progressBarMoviesCurrent.visibility = View.GONE
                        viewModel.movieCurrentError(true)
                        Snackbar.make(
                            requireView().rootView,
                            result.message ?: "Unknown error!!",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        })
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

            // fabUiNotLoading.setOnClickListener { adaptor.retry() }
        }
        return adaptor
    }

    private fun setupCurrentMoviesAdapter(): MoviesCurrentAdapter {
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