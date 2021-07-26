package com.bertholucci.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bertholucci.core.component.ARG_DESCRIPTION
import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.component.LoadingDialog
import com.bertholucci.core.exception.handleException

abstract class BaseFragment<T : ViewBinding>(
    private val layoutId: Int
) : Fragment() {

    lateinit var binding: T

    abstract fun getViewBinding(): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    //This function has to be called inside the children to configure loading dialog
    fun setupLoading(viewModel: BaseViewModel) {
        val loadingDialog = LoadingDialog()
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) loadingDialog.show(childFragmentManager, "LOADING_DIALOG")
            else loadingDialog.dismiss()
        }
    }

    fun handleError(error: Throwable) {
        showErrorDialog(handleException(context, error))
    }

    private fun showErrorDialog(@StringRes resId: Int) {
        val errorDialog = ErrorDialog()
        errorDialog.arguments = Bundle().apply { putString(ARG_DESCRIPTION, getString(resId)) }
        errorDialog.show(childFragmentManager, "")
    }
}