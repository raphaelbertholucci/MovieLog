package com.bertholucci.core.component

import android.app.Dialog
import androidx.core.content.res.ResourcesCompat
import com.bertholucci.core.R
import com.bertholucci.core.base.BaseDialogFragment
import com.bertholucci.core.databinding.DialogLoadingBinding

class LoadingDialog : BaseDialogFragment<DialogLoadingBinding>(R.layout.dialog_loading) {

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        dialog.window?.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                android.R.color.transparent,
                null
            )
        )
    }
}