package com.bertholucci.movie

import android.os.Bundle
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.core.model.Genre
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.EXTRA_ID
import com.bertholucci.data.helpers.fold
import com.bertholucci.movie.databinding.ActivityMovieBinding
import com.bertholucci.movie.extensions.toRuntime
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel

class MovieActivity : BaseActivity<ActivityMovieBinding>(R.layout.activity_movie) {

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        intent.extras?.getString(EXTRA_ID)?.let {
            viewModel.getMovieDetail(it)
        }
    }

    private fun addObservers() {
        viewModel.movie.observe(this) { response ->
            response.fold(error = ::handleError, success = ::handleSuccess)
        }
    }

    private fun handleSuccess(movie: Movie) {
        binding.movie = movie
        binding.ivBackground.loadFromUrl(movie.backdropPath)
        binding.tvRuntime.text = movie.runtime?.toRuntime()
        setupGenreAdapter(movie.genres)
    }

    private fun setupGenreAdapter(list: List<Genre>) {
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.rvGenre.layoutManager = layoutManager
    }
}