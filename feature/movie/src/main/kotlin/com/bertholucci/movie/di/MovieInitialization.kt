package com.bertholucci.movie.di

import com.bertholucci.movie.ui.MovieViewModel
import com.bertholucci.movie.ui.list.MovieListViewModel
import com.bertholucci.movie.ui.movie.MovieDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieViewModel(repository = get()) }
    viewModel { MovieListViewModel(repository = get()) }
    viewModel { MovieDetailsViewModel(repository = get()) }
}