package com.bertholucci.core.route

import android.content.Context
import com.bertholucci.core.extensions.intentForAction

const val ACTION_HOME = "HOME"

fun Context.intentToHome() = intentForAction(ACTION_HOME)