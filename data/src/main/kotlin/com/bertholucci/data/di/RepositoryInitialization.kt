package com.bertholucci.data.di

import com.bertholucci.data.repository.HomeRepository
import com.bertholucci.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(api = get()) }
    single { MovieRepository(api = get()) }
}
