package com.bertholucci.home.ui.top_rated

import android.os.Bundle
import android.view.View
import com.bertholucci.core.base.BaseFragment
import com.bertholucci.core.model.Movie
import com.bertholucci.core.route.intentToMovie
import com.bertholucci.data.helpers.fold
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentTopRatedBinding
import com.bertholucci.home.ui.home.HomeAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>(R.layout.fragment_top_rated) {

    private val viewModel: TopRatedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()

        viewModel.getTopRatedMovies()
    }

    private fun addObservers() {
        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            response.fold(::handleError, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<Movie>) {
        val adapter = HomeAdapter(list)
        adapter.onClick = { movie ->
            activity?.run { startActivity(intentToMovie(movie.id.toString())) }
        }
        binding.rvTopRated.adapter = TopRatedAdapter(list)
    }
}