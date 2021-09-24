package com.bertholucci.home.ui.favorites

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.extensions.setColor
import com.bertholucci.core.helpers.fold
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(R.layout.fragment_favorites) {

    private val viewModel: FavoritesViewModel by viewModel()

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onMovieActivityResult(result)
        }

    override fun getViewBinding() = FragmentFavoritesBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        addListeners()

        binding.swipe.setColor(context)
        viewModel.getFavoritesMovies()
    }

    private fun onMovieActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.getFavoritesMovies()
        }
    }

    private fun addObservers() {
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            binding.swipe.isRefreshing = false
            response.fold(::handleError, ::handleSuccess)
        }
    }

    private fun addListeners() {
        binding.swipe.setOnRefreshListener {
            viewModel.getFavoritesMovies()
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
        activity?.run { resultLauncher.launch(intentToMovie(id)) }
    }
}

const val ADAPTER_SPAN_COUNT = 2