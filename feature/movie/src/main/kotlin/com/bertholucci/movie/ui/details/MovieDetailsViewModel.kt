package com.bertholucci.movie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.helpers.Response
import com.bertholucci.core.mapper.MovieMapper
import com.bertholucci.core.model.Movie
import com.bertholucci.movielog.domain.interactor.GetMovieByIdFromDB
import com.bertholucci.movielog.domain.interactor.GetMovieDetails
import com.bertholucci.movielog.domain.interactor.InsertMovieIntoDB
import com.bertholucci.movielog.domain.interactor.RemoveMovieFromDB
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MovieDetailsViewModel(
    private val movieDetails: GetMovieDetails,
    private val movieByIdFromDB: GetMovieByIdFromDB,
    private val insertMovieIntoDB: InsertMovieIntoDB,
    private val removeMovieFromDB: RemoveMovieFromDB
) : BaseViewModel() {

    val isFavorite = MutableLiveData<Boolean>()
    var hasChanged = false

    private val _movie = MutableLiveData<Response<Movie>>()
    val movie: LiveData<Response<Movie>>
        get() = _movie

    private val _movieEntity = MutableLiveData<Response<Movie>>()
    val movieEntity: LiveData<Response<Movie>>
        get() = _movieEntity

    fun getMovieDetail(id: String) {
        movieDetails(id)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _movie.postValue(Response.Success(MovieMapper().mapFromDomain(it))) }
            .catch { _movie.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun updateMovieState(movie: Movie) {
        if (movie.isFavorite) removeMovie(movie)
        else insertMovie(movie)
    }

    private fun insertMovie(movie: Movie) {
        insertMovieIntoDB(MovieMapper().mapToDomain(movie))
            .map { isFavorite.postValue(true) }
            .launchIn(viewModelScope)
    }

    private fun removeMovie(movie: Movie) {
        removeMovieFromDB(MovieMapper().mapToDomain(movie))
            .map { isFavorite.postValue(false) }
            .launchIn(viewModelScope)
    }

    fun getMovieByIDFromDB(id: Int) {
        movieByIdFromDB(id)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { movie ->
                movie?.let {
                    _movieEntity.postValue(Response.Success(MovieMapper().mapFromDomain(it)))
                }
            }
            .catch { _movieEntity.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}