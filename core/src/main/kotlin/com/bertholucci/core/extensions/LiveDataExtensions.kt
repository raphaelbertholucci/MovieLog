package com.bertholucci.core.extensions

import androidx.lifecycle.MutableLiveData

fun <T : Any> MutableLiveData<T>.defaultValue(defaultValue: T, async: Boolean = false) =
    apply {
        if (value == null) {
            if (async) postValue(defaultValue)
        } else {
            value = defaultValue
        }
    }