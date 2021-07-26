package com.bertholucci.core.exception

sealed class Error : Throwable() {
    object ServerError : Error()
    abstract class FeatureError : Error()
}
