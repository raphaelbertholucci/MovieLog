package com.bertholucci.core.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bertholucci.core.R
import com.bertholucci.core.component.ARG_DESCRIPTION
import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.exception.Failure
import com.bertholucci.core.helpers.NetworkHelper

abstract class BaseActivity<T : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    fun handleError(error: Throwable) {
        when {
            NetworkHelper.hasConnection(context = this)
                .not() -> showErrorDialog(R.string.network_error)
            error is Failure.ServerFailure -> showErrorDialog(R.string.server_error)
            error is Failure.FeatureFailure -> showErrorDialog(R.string.feature_error)
            error is Failure.GenericFailure -> showErrorDialog(R.string.general_error)
        }
    }

    private fun showErrorDialog(@StringRes resId: Int) {
        val dialog = ErrorDialog()
        dialog.arguments = Bundle().apply { putString(ARG_DESCRIPTION, getString(resId)) }
        dialog.show(supportFragmentManager, "")
    }
}