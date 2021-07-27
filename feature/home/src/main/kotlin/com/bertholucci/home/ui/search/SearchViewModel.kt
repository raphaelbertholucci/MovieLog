package com.bertholucci.home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.extensions.defaultValue
import com.bertholucci.core.model.Movie
import com.bertholucci.data.helpers.Response
import com.bertholucci.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

private const val START_PAGE = 1

class SearchViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val page = MutableLiveData<Int>().defaultValue(START_PAGE)

    private val _result = MutableLiveData<Response<MutableList<Movie>>>()
    val result: LiveData<Response<MutableList<Movie>>>
        get() = _result

    fun searchMovies(query: String, page: Int = 1) {
        repository.searchMovies(query = query, page = page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _result.postValue(Response.Success(it.map(::Movie).toMutableList())) }
            .catch { _result.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun updatePage(index: Int) {
        page.value = index
    }

    fun getPage() = page.value ?: START_PAGE
}