package com.bertholucci.core.exception

import androidx.annotation.StringRes
import com.bertholucci.core.R

sealed class Failure(@StringRes val errorMessage: Int = R.string.general_error) : Throwable() {
    object ServerFailure : Failure()
    class GenericFailure : Failure()
    abstract class FeatureFailure : Failure()
}
