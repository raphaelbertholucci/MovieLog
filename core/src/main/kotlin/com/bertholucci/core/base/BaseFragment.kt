package com.bertholucci.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bertholucci.core.component.ARG_DESCRIPTION
import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.component.LoadingDialog
import com.bertholucci.core.exception.ExceptionHandler.handleException
import org.koin.android.ext.android.inject

abstract class BaseFragment<T : ViewDataBinding>(
    private val layoutId: Int
) : Fragment() {

    lateinit var binding: T

    private val errorDialog: ErrorDialog by inject()
    private val loadingDialog: LoadingDialog by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    //This function has to be called inside the children to configure loading dialog
    fun setupLoading(viewModel: BaseViewModel) {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) loadingDialog.show(childFragmentManager, "LOADING_DIALOG")
            else loadingDialog.dismiss()
        }
    }

    fun handleError(error: Throwable) {
        showErrorDialog(handleException(context, error))
    }

    private fun showErrorDialog(@StringRes resId: Int) {
        errorDialog.arguments = Bundle().apply { putString(ARG_DESCRIPTION, getString(resId)) }
        errorDialog.show(childFragmentManager, "")
    }
}