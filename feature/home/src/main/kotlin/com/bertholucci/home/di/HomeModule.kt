package com.bertholucci.home.di

import com.bertholucci.home.ui.favorites.FavoritesViewModel
import com.bertholucci.home.ui.home.HomeViewModel
import com.bertholucci.home.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(repository = get()) }
    viewModel { SearchViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }
}