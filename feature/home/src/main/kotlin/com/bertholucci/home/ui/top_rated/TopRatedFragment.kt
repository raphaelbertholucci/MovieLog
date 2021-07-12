package com.bertholucci.home.ui.top_rated

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.helpers.EndlessScrollListener
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.core.ui.MovieListAdapter
import com.bertholucci.data.helpers.fold
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentTopRatedBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>(R.layout.fragment_top_rated) {

    private val viewModel: TopRatedViewModel by viewModel()

    private val adapter = MovieListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setupUI()

        viewModel.getTopRatedMovies()
    }

    private fun addObservers() {
        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            response.fold(::handleError, ::handleSuccess)
        }
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.onClick = { movie ->
            activity?.run { startActivity(intentToMovie(movie.id.toString())) }
        }
        binding.rvTopRated.layoutManager = layoutManager
        binding.rvTopRated.adapter = adapter
        setupEndlessScroll(layoutManager, adapter)
    }

    private fun handleSuccess(list: MutableList<Movie>) {
        adapter.isLoading = false
        if (list.isNotEmpty()) {
            adapter.isFullyLoaded = false
            adapter.updateList(list)
        } else {
            adapter.isFullyLoaded = true
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupEndlessScroll(layoutManager: LinearLayoutManager, adapter: MovieListAdapter) {
        val endlessScrollListener = object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemCount: Int) {
                adapter.isLoading = true
                viewModel.getTopRatedMovies(page)
            }
        }
        binding.rvTopRated.addOnScrollListener(endlessScrollListener)
    }
}