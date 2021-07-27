package com.bertholucci.movie.di

import com.bertholucci.movie.ui.details.MovieDetailsViewModel
import com.bertholucci.movie.ui.list.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieListViewModel(repository = get()) }
    viewModel { MovieDetailsViewModel(repository = get()) }
}