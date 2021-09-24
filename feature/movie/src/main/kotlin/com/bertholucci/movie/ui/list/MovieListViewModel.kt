package com.bertholucci.movie.ui.list

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
import com.bertholucci.movielog.domain.model.MovieType
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MovieListViewModel(
    private val upcomingMovies: GetUpcomingMovies,
    private val popularMovies: GetPopularMovies,
    private val nowPlayingMovies: GetNowPlayingMovies,
    private val topRatedMovies: GetTopRatedMovies
) : BaseViewModel() {

    private val _movieList = MutableLiveData<Response<MutableList<Movie>>>()
    val movieList: LiveData<Response<MutableList<Movie>>>
        get() = _movieList

    fun getMovies(type: MovieType, page: Int = 1) {
        popularMovies
        useCase(type, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map {
                _movieList.postValue(
                    Response.Success(
                        MovieMapper().mapFromDomainList(it).toMutableList()
                    )
                )
            }
            .catch { _movieList.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    private fun useCase(type: MovieType, page: Int) = when (type) {
        MovieType.UPCOMING -> upcomingMovies(page)
        MovieType.POPULAR -> popularMovies(page)
        MovieType.NOW_PLAYING -> nowPlayingMovies(page)
        MovieType.TOP_RATED -> topRatedMovies(page)
    }
}