package com.bertholucci.data.di

import com.bertholucci.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(api = get(), dao = get()) }
}
