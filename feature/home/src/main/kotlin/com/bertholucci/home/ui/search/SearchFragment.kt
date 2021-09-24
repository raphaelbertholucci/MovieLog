package com.bertholucci.home.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.helpers.EndlessScrollListener
import com.bertholucci.core.helpers.fold
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.core.ui.MovieListAdapter
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()

    private val adapter = MovieListAdapter()

    override fun getViewBinding() = FragmentSearchBinding.inflate(LayoutInflater.from(context))

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
                    viewModel.updatePage(1)
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
            list.isNotEmpty() && viewModel.getPage() == 1 && list.size < 20 -> {
                adapter.isFullyLoaded = true
                adapter.setList(list)
            }
            list.isNotEmpty() && viewModel.getPage() == 1 && list.size == 20 -> {
                adapter.isFullyLoaded = false
                adapter.setList(list)
            }
            list.isNotEmpty() && viewModel.getPage() > 1 -> {
                adapter.isFullyLoaded = false
                adapter.updateList(list)
            }
        }
    }

    private fun setupEndlessScroll(layoutManager: LinearLayoutManager, adapter: MovieListAdapter) {
        val endlessScrollListener = object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemCount: Int) {
                adapter.isLoading = true
                viewModel.updatePage(page)
                viewModel.searchMovies(binding.etSearch.text.toString(), page)
            }
        }
        binding.rvMovies.addOnScrollListener(endlessScrollListener)
    }
}