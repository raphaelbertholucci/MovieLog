package com.bertholucci.movielog.domain

import kotlinx.coroutines.flow.Flow

abstract class UseCase<in REQUEST, RESPONSE> {

    operator fun invoke(requestValues: REQUEST): Flow<RESPONSE> {
        return executeUseCase(requestValues)
    }

    abstract fun executeUseCase(requestValues: REQUEST): Flow<RESPONSE>

    object None
}