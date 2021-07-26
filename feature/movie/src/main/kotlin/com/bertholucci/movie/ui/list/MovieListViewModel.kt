package com.bertholucci.movie.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.model.Movie
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.model.MovieType
import com.bertholucci.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MovieListViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val _movieList = MutableLiveData<Response<MutableList<Movie>>>()
    val movieList: LiveData<Response<MutableList<Movie>>>
        get() = _movieList

    fun getMovies(type: MovieType, page: Int = 1) {
        repository.getMoviesByType(type, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _movieList.postValue(Response.Success(it.map(::Movie).toMutableList())) }
            .catch { _movieList.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}