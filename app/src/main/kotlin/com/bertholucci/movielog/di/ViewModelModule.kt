package com.bertholucci.movielog.di

import com.bertholucci.movielog.ui.favorites.FavoritesViewModel
import com.bertholucci.movielog.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }
}