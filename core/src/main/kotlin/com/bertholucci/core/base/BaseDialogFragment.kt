package com.bertholucci.core.base

import android.app.Dialog
import android.util.DisplayMetrics
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

private const val width_scale = 0.90F

abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment() {

    lateinit var binding: T

    private var show = false

    abstract fun getViewBinding(): T

    override fun onResume() {
        super.onResume()
        val dialog = dialog
        val metrics = DisplayMetrics()
        activity?.display?.getRealMetrics(metrics)
        if (dialog != null) {
            dialog.window!!.setLayout(
                (metrics.widthPixels * width_scale).toInt(),
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        binding = getViewBinding()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(binding.root)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (show || isAdded || dialog != null && dialog!!.isShowing) return

        try {
            show = true
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        try {
            show = false
            super.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}