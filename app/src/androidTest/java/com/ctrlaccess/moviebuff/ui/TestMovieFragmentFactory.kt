package com.ctrlaccess.moviebuff.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.ctrlaccess.moviebuff.adapters.MoviesCurrentAdapter
import com.ctrlaccess.moviebuff.adapters.MoviesPopularAdaptor
import com.ctrlaccess.moviebuff.repo.FakeTestMoviesRepo
import com.ctrlaccess.moviebuff.ui.fragments.FragmentDetails
import com.ctrlaccess.moviebuff.ui.fragments.FragmentMain
import javax.inject.Inject

class TestMovieFragmentFactory @Inject constructor(
    private val moviesCurrentAdapter: MoviesCurrentAdapter,
    private val moviesPopularAdaptor: MoviesPopularAdaptor,
    private val glide: RequestManager,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            FragmentMain::class.java.name -> FragmentMain(
                moviesCurrentAdapter,
                moviesPopularAdaptor,
                MoviesViewModel(FakeTestMoviesRepo())
            )
            FragmentDetails::class.java.name -> FragmentDetails(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}
