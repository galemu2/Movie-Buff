package com.ctrlaccess.moviebuff.ui.fragment1

import androidx.paging.Pager
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.repo.MoviesRepo
import com.ctrlaccess.moviebuff.util.Event
import com.ctrlaccess.moviebuff.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MoviesRepo
) : ViewModel() {

    private val CURRENT_QUERY: String = "current query"

    private val _currentMovies = MutableLiveData<Event<Resource<MoviesCurrent>>>()
    val currentMovies: LiveData<Event<Resource<MoviesCurrent>>>
        get() = _currentMovies

    val popularMovies = repo.getPopularMovies().cachedIn(viewModelScope)

    fun getCurrentMovies() {
        _currentMovies.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repo.getCurrentMovies()
            _currentMovies.value = Event(response)
        }
    }

}