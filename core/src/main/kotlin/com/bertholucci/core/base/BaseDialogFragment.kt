package com.bertholucci.core.base

import android.app.Dialog
import android.util.DisplayMetrics
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

private const val width_scale = 0.90F

abstract class BaseDialogFragment<T : ViewDataBinding>(private val layoutId: Int) :
    DialogFragment() {

    lateinit var binding: T

    var show = false

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
        binding = DataBindingUtil.inflate(dialog.layoutInflater, layoutId, null, false)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(binding.root)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (show || dialog != null && dialog!!.isShowing) return

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