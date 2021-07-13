package com.bertholucci.movielog.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.helpers.EndlessScrollListener
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.data.helpers.fold
import com.bertholucci.movie.model.Movie
import com.bertholucci.movie.ui.list.MovieListAdapter
import com.bertholucci.movielog.R
import com.bertholucci.movielog.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()

    private val adapter = MovieListAdapter()
    private var searchPage = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setupAdapter()
        setupListeners()
    }

    private fun setupListeners() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.length >= 3) {
                    searchPage = 1
                    viewModel.searchMovies(text.toString())
                }
            }
        }
    }

    private fun addObservers() {
        viewModel.result.observe(viewLifecycleOwner) { response ->
            response.fold(::handleError, ::handleSearchSuccess)

        }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.onClick = { movie ->
            activity?.run { startActivity(intentToMovie(movie.id.toString())) }
        }
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter
        setupEndlessScroll(layoutManager, adapter)
    }

    private fun handleSearchSuccess(list: MutableList<Movie>) {
        adapter.isLoading = false
        when {
            list.isEmpty() -> {
                adapter.isFullyLoaded = true
                adapter.notifyDataSetChanged()
            }
            list.isNotEmpty() && searchPage == 1 && list.size < 20 -> {
                adapter.isFullyLoaded = true
                adapter.setList(list)
            }
            list.isNotEmpty() && searchPage == 1 && list.size == 20 -> {
                adapter.isFullyLoaded = false
                adapter.setList(list)
            }
            list.isNotEmpty() && searchPage > 1 -> {
                adapter.isFullyLoaded = false
                adapter.updateList(list)
            }
        }
    }

    private fun setupEndlessScroll(layoutManager: LinearLayoutManager, adapter: MovieListAdapter) {
        val endlessScrollListener = object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemCount: Int) {
                adapter.isLoading = true
                searchPage = page
                viewModel.searchMovies(binding.etSearch.text.toString(), page)
            }
        }
        binding.rvMovies.addOnScrollListener(endlessScrollListener)
    }
}