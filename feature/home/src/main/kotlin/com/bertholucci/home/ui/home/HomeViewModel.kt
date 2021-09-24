package com.bertholucci.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.helpers.Response
import com.bertholucci.core.mapper.MovieMapper
import com.bertholucci.core.model.Movie
import com.bertholucci.movielog.domain.interactor.GetNowPlayingMovies
import com.bertholucci.movielog.domain.interactor.GetPopularMovies
import com.bertholucci.movielog.domain.interactor.GetTopRatedMovies
import com.bertholucci.movielog.domain.interactor.GetUpcomingMovies
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class HomeViewModel(
    private val upcomingMovies: GetUpcomingMovies,
    private val popularMovies: GetPopularMovies,
    private val nowPlayingMovies: GetNowPlayingMovies,
    private val topRatedMovies: GetTopRatedMovies
) : BaseViewModel() {

    private val _upcoming = MutableLiveData<Response<List<Movie>>>()
    val upcoming: LiveData<Response<List<Movie>>>
        get() = _upcoming

    private val _popular = MutableLiveData<Response<List<Movie>>>()
    val popular: LiveData<Response<List<Movie>>>
        get() = _popular

    private val _nowPlaying = MutableLiveData<Response<List<Movie>>>()
    val nowPlaying: LiveData<Response<List<Movie>>>
        get() = _nowPlaying

    private val _topRated = MutableLiveData<Response<List<Movie>>>()
    val topRated: LiveData<Response<List<Movie>>>
        get() = _topRated

    fun getUpcomingMovies(page: Int = 1) {
        upcomingMovies(page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _upcoming.postValue(Response.Success(MovieMapper().mapFromDomainList(it))) }
            .catch { _upcoming.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getPopularMovies(page: Int = 1) {
        popularMovies(page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _popular.postValue(Response.Success(MovieMapper().mapFromDomainList(it))) }
            .catch { _popular.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getNowPlayingMovies(page: Int = 1) {
        nowPlayingMovies(page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _nowPlaying.postValue(Response.Success(MovieMapper().mapFromDomainList(it))) }
            .catch { _nowPlaying.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun getTopRatedMovies(page: Int = 1) {
        topRatedMovies(page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _topRated.postValue(Response.Success(MovieMapper().mapFromDomainList(it))) }
            .catch { _topRated.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}