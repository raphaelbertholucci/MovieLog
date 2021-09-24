package com.bertholucci.data.di

import com.bertholucci.data.repository.MovieRepositoryImpl
import com.bertholucci.movielog.domain.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(api = get(), dao = get()) }
}
