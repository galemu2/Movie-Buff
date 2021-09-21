package com.ctrlaccess.moviebuff.ui.fragment1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ctrlaccess.moviebuff.data.model.MoviesCurrent
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

    private val TAG: String = "ViewModel"
    private val _movieCurrentError = MutableLiveData(false)
    val movieCurrentError: LiveData<Boolean>
        get() = _movieCurrentError


    private val _moviePopularError = MutableLiveData(false)
    val moviePopularError: LiveData<Boolean>
        get() = _moviePopularError

    private val _moviesError = MutableLiveData<List<Boolean>>(listOf(false, false))
    val moviesError: LiveData<List<Boolean>>
        get() = _moviesError


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

    fun movieCurrentError(boolean: Boolean) {
         _movieCurrentError.value = boolean
    }

    fun moviePopularError(boolean: Boolean) {
        _moviePopularError.value = boolean
    }

}