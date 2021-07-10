package com.bertholucci.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bertholucci.core.R
import com.bertholucci.core.component.ARG_DESCRIPTION
import com.bertholucci.core.component.ErrorDialog
import com.bertholucci.core.component.LoadingDialog
import com.bertholucci.core.exception.Failure
import com.bertholucci.core.helpers.NetworkHelper

abstract class BaseFragment<T : ViewDataBinding>(
    private val layoutId: Int
) : Fragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    fun setupLoading(viewModel: BaseViewModel) {
        val loadingDialog = LoadingDialog()

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            loadingDialog.show = loading
        }
    }

    fun handleError(error: Throwable) {
        when {
            NetworkHelper.hasConnection(context = context)
                .not() -> showErrorDialog(R.string.network_error)
            error is Failure.ServerFailure -> showErrorDialog(R.string.server_error)
            error is Failure.FeatureFailure -> showErrorDialog(R.string.feature_error)
            error is Failure.GenericFailure -> showErrorDialog(R.string.general_error)
        }
    }

    private fun showErrorDialog(@StringRes resId: Int) {
        val dialog = ErrorDialog()
        dialog.arguments = Bundle().apply { putString(ARG_DESCRIPTION, getString(resId)) }
        dialog.show = true
    }
}