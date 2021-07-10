package com.bertholucci.home.ui.home

import android.os.Bundle
import android.view.View
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.component.LoadingDialog
import com.bertholucci.data.helpers.fold
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentHomeBinding
import com.bertholucci.home.model.Movie
import com.bertholucci.home.model.MovieType
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoading(viewModel)
        addObservers()
        addListeners()

        viewModel.getUpcomingMovies()
        viewModel.getPopularMovies()
        viewModel.getNowPlayingMovies()
    }

    private fun addObservers() {
        viewModel.upcoming.observe(viewLifecycleOwner) { response ->
            response.fold(
                success = ::handleUpcomingSuccess,
                loading = ::handleLoading,
                error = ::handleError
            )
        }

        viewModel.popular.observe(viewLifecycleOwner) { response ->
            response.fold(
                success = ::handlePopularSuccess,
                loading = ::handleLoading,
                error = ::handleError
            )
        }

        viewModel.nowPlaying.observe(viewLifecycleOwner) { response ->
            response.fold(
                success = ::handleNowPlayingSuccess,
                loading = ::handleLoading,
                error = ::handleError
            )
        }
    }

    private fun addListeners() {
        binding.tvSeeAllUpcoming.setOnClickListener {
            navigateToList(MovieType.UPCOMING)
        }
        binding.tvSeeAllPopular.setOnClickListener {
            navigateToList(MovieType.POPULAR)
        }
        binding.tvSeeAllNowPlaying.setOnClickListener {
            navigateToList(MovieType.NOW_PLAYING)
        }
    }

    private fun handleUpcomingSuccess(list: List<Movie>) {
        val adapter = HomeAdapter(list)
        binding.rvUpcoming.adapter = adapter
    }

    private fun handlePopularSuccess(list: List<Movie>) {
        val adapter = HomeAdapter(list)
        binding.rvPopular.adapter = adapter
    }

    private fun handleNowPlayingSuccess(list: List<Movie>) {
        val adapter = HomeAdapter(list)
        binding.rvNowPlaying.adapter = adapter
    }

    private fun handleLoading(loading: Boolean) {
        LoadingDialog().show = loading
    }

    private fun navigateToList(type: MovieType) {

    }
}