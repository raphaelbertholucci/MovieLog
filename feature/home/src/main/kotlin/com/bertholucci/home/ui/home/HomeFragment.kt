package com.bertholucci.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.core.route.intentToMovieList
import com.bertholucci.data.helpers.fold
import com.bertholucci.data.model.MovieType
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    override fun getViewBinding() = FragmentHomeBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoading(viewModel)
        addObservers()
        addListeners()

        viewModel.getUpcomingMovies()
        viewModel.getPopularMovies()
        viewModel.getNowPlayingMovies()
        viewModel.getTopRatedMovies()
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

        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            response.fold(
                success = ::handleTopRatedSuccess,
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
        binding.tvSeeAllTopRated.setOnClickListener {
            navigateToList(MovieType.TOP_RATED)
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

    private fun handleTopRatedSuccess(list: List<Movie>) {
        binding.rvTopRated.adapter = setupAdapter(list)
    }

    private fun setupAdapter(list: List<Movie>): HomeAdapter {
        val adapter = HomeAdapter(list)
        adapter.onClick = {
            navigateToMovieDetails(it.id.toString())
        }
        return adapter
    }

    private fun navigateToList(type: MovieType) {
        activity?.run { startActivity(intentToMovieList(type.toString())) }
    }

    private fun navigateToMovieDetails(id: String) {
        activity?.run { startActivity(intentToMovie(id)) }
    }
}