package com.bertholucci.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.model.Movie
import com.bertholucci.core.model.toEntityRequest
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MovieViewModel(private val repository: MovieRepository) : BaseViewModel() {

    val isFavorite = MutableLiveData<Boolean>()

    private val _movie = MutableLiveData<Response<Movie>>()
    val movie: LiveData<Response<Movie>>
        get() = _movie

    private val _movieEntity = MutableLiveData<Response<Movie>>()
    val movieEntity: LiveData<Response<Movie>>
        get() = _movieEntity

    fun getMovieDetail(id: String) {
        repository.getMovieDetails(id)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _movie.postValue(Response.Success(Movie(it))) }
            .catch { _movie.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun insertMovie(movie: Movie) {
        repository.insertMovie(movie.toEntityRequest())
        isFavorite.postValue(true)
    }

    fun removeMovie(movie: Movie) {
        repository.removeMovie(movie.toEntityRequest())
        isFavorite.postValue(false)
    }

    fun getMovieByID(id: Int) {
        repository.getMovieById(id)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _movieEntity.postValue(Response.Success(Movie(it))) }
            .catch { _movieEntity.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}