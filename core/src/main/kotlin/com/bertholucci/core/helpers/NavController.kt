package com.bertholucci.core.helpers

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlin.reflect.KProperty

fun AppCompatActivity.navProvider(@IdRes idRes: Int) = NavControllerProvider(idRes)

fun Fragment.navProvider() = NavControllerProvider()

class NavControllerProvider(@IdRes private val idRes: Int = 0) {

    operator fun getValue(ref: AppCompatActivity, property: KProperty<*>): NavController {
        return ref.findNavController(idRes)
    }

    operator fun getValue(ref: Fragment, property: KProperty<*>): NavController {
        return ref.findNavController()
    }
}