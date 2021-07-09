package com.bertholucci.core.extensions

import android.content.Context
import android.content.Intent

fun Context.intentForAction(action: String, vararg flags: Int) = Intent().apply {
    setAction("$packageName.$action")
    flags.forEach { addFlags(it) }
}