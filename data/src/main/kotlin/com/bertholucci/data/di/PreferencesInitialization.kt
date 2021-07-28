package com.bertholucci.data.di

import com.bertholucci.data.preferences.MovieLogEncryptedPrefs
import com.bertholucci.data.preferences.MovieLogPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single { MovieLogPrefs(androidContext()) }
    single { MovieLogEncryptedPrefs(androidContext()) }
}