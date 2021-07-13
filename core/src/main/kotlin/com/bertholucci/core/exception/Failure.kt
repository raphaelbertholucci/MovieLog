package com.bertholucci.core.exception

sealed class Failure : Throwable() {
    object ServerFailure : Failure()
    abstract class FeatureFailure : Failure()
}
