package com.bertholucci.core.component

import android.os.Bundle
import android.view.View
import com.bertholucci.core.R
import com.bertholucci.core.base.BaseDialogFragment
import com.bertholucci.core.databinding.DialogErrorBinding

const val ARG_DESCRIPTION = "ARG_DESCRIPTION"

class ErrorDialog : BaseDialogFragment<DialogErrorBinding>(R.layout.dialog_error) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            binding.tvDescription.text = getString(ARG_DESCRIPTION)
        }
    }
}