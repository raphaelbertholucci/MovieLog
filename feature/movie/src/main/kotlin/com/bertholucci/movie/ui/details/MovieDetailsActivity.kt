package com.bertholucci.movie.ui.details

import android.app.Activity
import android.os.Bundle
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.core.extensions.showSnack
import com.bertholucci.core.helpers.fold
import com.bertholucci.core.model.Genre
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.EXTRA_ID
import com.bertholucci.movie.R
import com.bertholucci.movie.databinding.ActivityMovieBinding
import com.bertholucci.movie.extensions.isFavorite
import com.bertholucci.movie.extensions.isNotFavorite
import com.bertholucci.movie.extensions.toRuntime
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : BaseActivity<ActivityMovieBinding>() {

    private val viewModel: MovieDetailsViewModel by viewModel()

    private lateinit var movie: Movie

    override fun getViewBinding() = ActivityMovieBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoading(viewModel)
        addObservers()
        addListeners()
        intent.extras?.getString(EXTRA_ID)?.let { id ->
            viewModel.getMovieDetail(id)
        }
    }

    override fun onBackPressed() {
        if (viewModel.hasChanged) setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }

    private fun addObservers() {
        viewModel.movie.observe(this) { response ->
            response.fold(error = ::handleError, success = ::handleSuccess)
        }

        viewModel.isFavorite.observe(this) { isFavorite ->
            checkIfMovieIsFavorite(isFavorite)
            viewModel.hasChanged = true
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
        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.ivSave.setOnClickListener {
            viewModel.updateMovieState(movie)
        }
    }

    private fun handleSuccess(movie: Movie) {
        viewModel.getMovieByIDFromDB(movie.id)
        this.movie = movie
        setupUI(movie)
    }

    private fun setupUI(movie: Movie) {
        binding.tvTitle.text = movie.title
        binding.tvLanguage.text = movie.originalLanguage?.uppercase()
        binding.tvReleaseDate.text = movie.releaseDate
        binding.tvRate.text = movie.voteAverage.toString()
        binding.ivBackground.loadFromUrl(movie.backdropPath)
        binding.ivPoster.loadFromUrl(movie.posterPath)
        binding.tvRuntime.text = movie.runtime?.toRuntime()
        binding.tvAbout.text = movie.overview
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