package com.bertholucci.home.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.helpers.Response
import com.bertholucci.core.model.Movie
import com.bertholucci.core.mapper.MovieMapper
import com.bertholucci.movielog.domain.UseCase
import com.bertholucci.movielog.domain.interactor.GetFavorites
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class FavoritesViewModel(private val getFavorites: GetFavorites) : BaseViewModel() {

    private val _movies = MutableLiveData<Response<List<Movie>>>()
    val movies: LiveData<Response<List<Movie>>>
        get() = _movies

    fun getFavoritesMovies() {
        getFavorites(UseCase.None)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { list ->
                list?.let { _movies.postValue(Response.Success(MovieMapper().mapFromDomainList(it))) }
            }
            .catch { _movies.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}