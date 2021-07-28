package com.bertholucci.home.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.data.helpers.fold
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentFavoritesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(R.layout.fragment_favorites) {

    private val viewModel: FavoritesViewModel by viewModel()

    override fun getViewBinding() = FragmentFavoritesBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()

        viewModel.getFavoritesMovies()
    }

    private fun addObservers() {
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            response.fold(::handleError, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<Movie>) {
        display(list.isEmpty())
        setupAdapter(list)
    }

    private fun display(isEmpty: Boolean) {
        binding.rvMovies.isVisible = isEmpty.not()
        binding.tvNoResults.isVisible = isEmpty
    }

    private fun setupAdapter(list: List<Movie>) {
        binding.rvMovies.layoutManager =
            StaggeredGridLayoutManager(ADAPTER_SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        val adapter = FavoritesAdapter(list)
        binding.rvMovies.adapter = adapter
        setupAdapterOnClickItem(adapter)
    }

    private fun setupAdapterOnClickItem(adapter: FavoritesAdapter) {
        adapter.onClick = { movie ->
            navigateToMovieDetails(movie.id.toString())
        }
    }

    private fun navigateToMovieDetails(id: String) {
        activity?.run { startActivity(intentToMovie(id)) }
    }
}

const val ADAPTER_SPAN_COUNT = 2