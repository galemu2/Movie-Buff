package com.ctrlaccess.moviebuff.ui.fragment1

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ctrlaccess.moviebuff.data.MoviesPagingSource
import com.ctrlaccess.moviebuff.data.Result
import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import com.ctrlaccess.moviebuff.repo.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MoviesRepo
) : ViewModel() {

    private val _currentMovies = MutableLiveData<List<Result>>()
    val currentMovies: LiveData<List<Result>>
        get() = _currentMovies

    private val _popularMovies = MutableLiveData<List<Result>>()
    val popularMovies: LiveData<List<Result>>
        get() = _popularMovies

    init {
        getCurrentMovies()
    }

    private fun getCurrentMovies() {
        viewModelScope.launch {
            _currentMovies.value = repo.getCurrentMovies().results
        }
    }

    val flow = repo.getPopularMovies().cachedIn(viewModelScope)


}