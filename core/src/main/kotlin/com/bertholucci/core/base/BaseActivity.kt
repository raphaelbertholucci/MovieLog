package com.bertholucci.core.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.bertholucci.core.component.ARG_DESCRIPTION
import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.component.LoadingDialog
import com.bertholucci.core.exception.ExceptionHandler.handleException
import org.koin.android.ext.android.inject

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T

    private val errorDialog: ErrorDialog by inject()
    private val loadingDialog: LoadingDialog by inject()

    abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    //This function has to be called inside the children to configure loading dialog
    fun setupLoading(viewModel: BaseViewModel) {
        viewModel.loading.observe(this) { loading ->
            if (loading) loadingDialog.show(supportFragmentManager, "LOADING_DIALOG")
            else loadingDialog.dismiss()
        }
    }

    fun handleError(error: Throwable) {
        showErrorDialog(handleException(this, error))
    }

    private fun showErrorDialog(@StringRes resId: Int) {
        errorDialog.arguments = Bundle().apply { putString(ARG_DESCRIPTION, getString(resId)) }
        errorDialog.show(supportFragmentManager, "")
    }
}