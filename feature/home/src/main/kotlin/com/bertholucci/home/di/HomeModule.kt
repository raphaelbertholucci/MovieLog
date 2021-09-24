package com.bertholucci.home.di

import com.bertholucci.home.ui.favorites.FavoritesViewModel
import com.bertholucci.home.ui.home.HomeViewModel
import com.bertholucci.home.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            upcomingMovies = get(),
            popularMovies = get(),
            nowPlayingMovies = get(),
            topRatedMovies = get()
        )
    }
    viewModel { SearchViewModel(searchMovies = get()) }
    viewModel { FavoritesViewModel(getFavorites = get()) }
}