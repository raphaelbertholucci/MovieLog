package com.bertholucci.movielog

import android.app.Application
import com.bertholucci.data.apiModule
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
                listOf(apiModule)
            )
        }
    }
}