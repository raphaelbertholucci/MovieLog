package com.bertholucci.home.di

import com.bertholucci.home.ui.home.HomeViewModel
import com.bertholucci.home.ui.top_rated.TopRatedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(repository = get()) }
    viewModel { TopRatedViewModel(repository = get()) }
}
