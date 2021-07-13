package com.bertholucci.core.exception

import android.content.Context
import com.bertholucci.core.R
import com.bertholucci.core.helpers.NetworkHelper

object ExceptionHandler {

    fun handleException(context: Context?, error: Throwable): Int {
        return when {
            NetworkHelper.hasConnection(context).not() -> R.string.network_error
            error is Failure.ServerFailure -> R.string.server_error
            error is Failure.FeatureFailure -> R.string.feature_error
            else -> R.string.general_error
        }
    }
}