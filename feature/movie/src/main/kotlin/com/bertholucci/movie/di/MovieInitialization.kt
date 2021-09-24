package com.bertholucci.movie.di

import com.bertholucci.movie.ui.details.MovieDetailsViewModel
import com.bertholucci.movie.ui.list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel {
        MovieListViewModel(
            upcomingMovies = get(),
            popularMovies = get(),
            nowPlayingMovies = get(),
            topRatedMovies = get()
        )
    }
    viewModel {
        MovieDetailsViewModel(
            movieDetails = get(),
            movieByIdFromDB = get(),
            insertMovieIntoDB = get(),
            removeMovieFromDB = get()
        )
    }
}