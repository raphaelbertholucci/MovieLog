package com.bertholucci.home.ui.top_rated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.model.Movie
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.repository.TopRatedRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class TopRatedViewModel(private val repository: TopRatedRepository) : BaseViewModel() {

    private val _topRated = MutableLiveData<Response<List<Movie>>>()
    val topRated: LiveData<Response<List<Movie>>>
        get() = _topRated

    fun getTopRatedMovies() {
        repository.getTopRatedMovies()
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _topRated.postValue(Response.Success(it.map(::Movie))) }
            .catch { _topRated.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}