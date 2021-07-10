package com.bertholucci.movielog

import android.app.Application
import com.bertholucci.data.di.apiModule
import com.bertholucci.data.di.repositoryModule
import com.bertholucci.home.di.homeModule
import com.bertholucci.movie.movieModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieLogApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieLogApp)
            androidLogger()
            modules(
                listOf(apiModule, repositoryModule, homeModule, movieModule)
            )
        }
    }
}