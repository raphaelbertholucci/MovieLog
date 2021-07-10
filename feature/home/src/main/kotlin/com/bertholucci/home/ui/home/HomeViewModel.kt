package com.bertholucci.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.repository.HomeRepository
import com.bertholucci.home.model.Movie
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val _upcoming = MutableLiveData<Response<List<Movie>>>()
    val upcoming: LiveData<Response<List<Movie>>>
        get() = _upcoming

    private val _popular = MutableLiveData<Response<List<Movie>>>()
    val popular: LiveData<Response<List<Movie>>>
        get() = _popular

    private val _nowPlaying = MutableLiveData<Response<List<Movie>>>()
    val nowPlaying: LiveData<Response<List<Movie>>>
        get() = _nowPlaying

    fun getUpcomingMovies() {
        repository.getUpcomingMovies()
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _upcoming.postValue(Response.Success(it.map(::Movie))) }
            .catch { _upcoming.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getPopularMovies() {
        repository.getPopularMovies()
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _popular.postValue(Response.Success(it.map(::Movie))) }
            .catch { _popular.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getNowPlayingMovies() {
        repository.getPopularMovies()
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _nowPlaying.postValue(Response.Success(it.map(::Movie))) }
            .catch { _nowPlaying.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}