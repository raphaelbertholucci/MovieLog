package com.bertholucci.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.model.Movie
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MovieViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val _movie = MutableLiveData<Response<Movie>>()
    val movie: LiveData<Response<Movie>>
        get() = _movie

    fun getMovieDetail(id: String) {
        repository.getMovieDetails(id)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _movie.postValue(Response.Success(Movie(it))) }
            .catch { _movie.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}