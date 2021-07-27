package com.bertholucci.home.ui.home

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

class HomeViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val _upcoming = MutableLiveData<Response<List<Movie>>>()
    val upcoming: LiveData<Response<List<Movie>>>
        get() = _upcoming

    private val _popular = MutableLiveData<Response<List<Movie>>>()
    val popular: LiveData<Response<List<Movie>>>
        get() = _popular

    private val _nowPlaying = MutableLiveData<Response<List<Movie>>>()
    val nowPlaying: LiveData<Response<List<Movie>>>
        get() = _nowPlaying

    private val _topRated = MutableLiveData<Response<MutableList<Movie>>>()
    val topRated: LiveData<Response<MutableList<Movie>>>
        get() = _topRated

    fun getUpcomingMovies(page: Int = 1) {
        repository.getMoviesByType(MovieType.UPCOMING, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _upcoming.postValue(Response.Success(it.map(::Movie))) }
            .catch { _upcoming.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getPopularMovies(page: Int = 1) {
        repository.getMoviesByType(MovieType.POPULAR, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _popular.postValue(Response.Success(it.map(::Movie))) }
            .catch { _popular.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getNowPlayingMovies(page: Int = 1) {
        repository.getMoviesByType(MovieType.NOW_PLAYING, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _nowPlaying.postValue(Response.Success(it.map(::Movie))) }
            .catch { _nowPlaying.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getTopRatedMovies(page: Int = 1) {
        repository.getMoviesByType(MovieType.TOP_RATED, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _topRated.postValue(Response.Success(it.map(::Movie).toMutableList())) }
            .catch { _topRated.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}