package com.bertholucci.core.exception

interface ErrorHandler {

    fun getError(throwable: Throwable): Error
}