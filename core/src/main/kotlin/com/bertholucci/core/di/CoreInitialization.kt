package com.bertholucci.core.di

import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.component.LoadingDialog
import org.koin.dsl.module

val coreModule = module {
    single { LoadingDialog() }
    single { ErrorDialog() }
}