package com.bertholucci.home.ui.home

import android.os.Bundle
import android.view.View
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.data.helpers.fold
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentHomeBinding
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
                error = ::handleError
            )
        }

        viewModel.popular.observe(viewLifecycleOwner) { response ->
            response.fold(
                success = ::handlePopularSuccess,
                error = ::handleError
            )
        }

        viewModel.nowPlaying.observe(viewLifecycleOwner) { response ->
            response.fold(
                success = ::handleNowPlayingSuccess,
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
        binding.rvUpcoming.adapter = setupAdapter(list)
    }

    private fun handlePopularSuccess(list: List<Movie>) {
        binding.rvPopular.adapter = setupAdapter(list)
    }

    private fun handleNowPlayingSuccess(list: List<Movie>) {
        binding.rvNowPlaying.adapter = setupAdapter(list)
    }

    private fun setupAdapter(list: List<Movie>): HomeAdapter {
        val adapter = HomeAdapter(list)
        adapter.onClick = {
            navigateToMovie(it.id.toString())
        }
        return adapter
    }

    private fun navigateToList(type: MovieType) {

    }

    private fun navigateToMovie(id: String) {
        activity?.run { startActivity(intentToMovie(id)) }
    }
}