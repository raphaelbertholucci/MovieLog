package com.bertholucci.home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bertholucci.core.base.BaseViewModel
import com.bertholucci.core.extensions.defaultValue
import com.bertholucci.core.helpers.Response
import com.bertholucci.core.model.Movie
import com.bertholucci.core.mapper.MovieMapper
import com.bertholucci.movielog.domain.interactor.SearchMovies
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

private const val START_PAGE = 1

class SearchViewModel(private val searchMovies: SearchMovies) : BaseViewModel() {

    private val page = MutableLiveData<Int>().defaultValue(START_PAGE)

    private val _result = MutableLiveData<Response<MutableList<Movie>>>()
    val result: LiveData<Response<MutableList<Movie>>>
        get() = _result

    fun searchMovies(query: String, page: Int = 1) {
        searchMovies(requestValues = Pair(query, page))
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map {
                _result.postValue(
                    Response.Success(
                        MovieMapper().mapFromDomainList(it).toMutableList()
                    )
                )
            }
            .catch { _result.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun updatePage(index: Int) {
        page.value = index
    }

    fun getPage() = page.value ?: START_PAGE
}