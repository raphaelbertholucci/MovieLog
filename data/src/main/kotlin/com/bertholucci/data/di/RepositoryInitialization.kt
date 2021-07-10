package com.bertholucci.data.di

import com.bertholucci.data.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(api = get()) }
}
