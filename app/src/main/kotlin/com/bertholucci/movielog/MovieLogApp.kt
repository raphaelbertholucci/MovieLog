package com.bertholucci.movielog

import android.app.Application
import com.bertholucci.core.di.coreModule
import com.bertholucci.data.di.apiModule
import com.bertholucci.data.di.databaseModule
import com.bertholucci.data.di.repositoryModule
import com.bertholucci.movie.di.movieModule
import com.bertholucci.movielog.di.viewModelModule
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
                listOf(
                    apiModule,
                    databaseModule,
                    repositoryModule,
                    movieModule,
                    coreModule,
                    viewModelModule
                )
            )
        }
    }
}