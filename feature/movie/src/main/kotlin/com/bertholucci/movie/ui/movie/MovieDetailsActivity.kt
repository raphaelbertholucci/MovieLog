package com.bertholucci.movie.ui.movie

import android.os.Bundle
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.core.extensions.showSnack
import com.bertholucci.movie.model.Genre
import com.bertholucci.movie.model.Movie
import com.bertholucci.core.route.EXTRA_ID
import com.bertholucci.data.helpers.fold
import com.bertholucci.movie.R
import com.bertholucci.movie.databinding.ActivityMovieBinding
import com.bertholucci.movie.extensions.isFavorite
import com.bertholucci.movie.extensions.isNotFavorite
import com.bertholucci.movie.extensions.toRuntime
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsActivity : BaseActivity<ActivityMovieBinding>(R.layout.activity_movie) {

    private val viewModel: MovieDetailsViewModel by viewModel()

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        addListeners()
        intent.extras?.getString(EXTRA_ID)?.let { id ->
            viewModel.getMovieDetail(id)
        }
    }

    private fun addObservers() {
        viewModel.movie.observe(this) { response ->
            response.fold(error = ::handleError, success = ::handleSuccess)
        }

        viewModel.isFavorite.observe(this) { isFavorite ->
            checkIfMovieIsFavorite(isFavorite)
        }

        viewModel.movieEntity.observe(this) { response ->
            response.fold(::handleError) {
                if (movie.id == it.id) {
                    movie.isFavorite = true
                    binding.ivSave.isFavorite()
                }
            }
        }
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener { finish() }
        binding.ivSave.setOnClickListener {
            viewModel.updateMovieState(movie)
        }
    }

    private fun handleSuccess(movie: Movie) {
        viewModel.getMovieByID(movie.id)
        this.movie = movie
        setupUI(movie)
    }

    private fun setupUI(movie: Movie) {
        binding.movie = movie
        binding.ivBackground.loadFromUrl(movie.backdropPath)
        binding.ivPoster.loadFromUrl(movie.posterPath)
        binding.tvRuntime.text = movie.runtime?.toRuntime()
        setupGenreAdapter(movie.genres)
    }

    private fun checkIfMovieIsFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            movie.isFavorite = true
            binding.ivSave.isFavorite()
            showSnack(R.string.movie_saved)
        } else {
            movie.isFavorite = false
            binding.ivSave.isNotFavorite()
            showSnack(R.string.movie_removed)
        }
    }

    private fun setupGenreAdapter(list: List<Genre>) {
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.rvGenre.layoutManager = layoutManager
        binding.rvGenre.adapter = GenreAdapter(list)
    }
}