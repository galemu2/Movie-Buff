package com.ctrlaccess.moviebuff.ui.fragment1

import androidx.paging.Pager
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.repo.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MoviesRepo
) : ViewModel() {

    private val CURRENT_QUERY: String = "current query"

    private val _currentMovies = MutableLiveData<List<Result>>()
    val currentMovies: LiveData<List<Result>>
        get() = _currentMovies

    val popularMovies  = repo.getPopularMovies().cachedIn(viewModelScope)

    init {
        getCurrentMovies()
    }

    private fun getCurrentMovies() {
        viewModelScope.launch {
            _currentMovies.value = repo.getCurrentMovies().results
        }
    }

    //val flow = repo.getPopularMovies().cachedIn(viewModelScope)


}