package com.easytraining.home.di

import com.easytraining.home.ui.favorites.FavoritesViewModel
import com.easytraining.home.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { SearchViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }
}