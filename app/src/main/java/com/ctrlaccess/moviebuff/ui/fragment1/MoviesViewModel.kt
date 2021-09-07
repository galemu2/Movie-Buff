package com.ctrlaccess.moviebuff.ui.fragment1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctrlaccess.moviebuff.data.Result
import com.ctrlaccess.moviebuff.repositories.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MoviesRepo) : ViewModel() {

    private val _currentMovies = MutableLiveData<List<Result>>()
    val currentMovies: LiveData<List<Result>>
        get() = _currentMovies

    init {
        getCurrentMovies()

    }

    private fun getCurrentMovies() {
        viewModelScope.launch {
            _currentMovies.value = repo.getCurrentMovies().results
        }
    }

}