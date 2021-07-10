package com.bertholucci.core.component

import android.app.Dialog
import com.bertholucci.core.R
import com.bertholucci.core.base.BaseDialogFragment
import com.bertholucci.core.databinding.DialogErrorBinding

const val ARG_DESCRIPTION = "ARG_DESCRIPTION"

class ErrorDialog : BaseDialogFragment<DialogErrorBinding>(R.layout.dialog_error) {

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        arguments?.apply {
            binding.tvDescription.text = getString(ARG_DESCRIPTION)
        }
        binding.btnUnderstood.setOnClickListener {
            this.dismiss()
        }
    }
}