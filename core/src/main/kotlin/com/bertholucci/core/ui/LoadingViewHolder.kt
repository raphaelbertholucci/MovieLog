package com.bertholucci.core.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.databinding.ItemLoadingBinding
import com.bertholucci.core.extensions.gone
import com.bertholucci.core.extensions.visible

class LoadingViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var binding: ItemLoadingBinding? = DataBindingUtil.bind(itemView)

    fun bind(isFullyLoaded: Boolean) {
        if (isFullyLoaded) {
            binding?.loading?.gone()
            binding?.tvFullyLoaded?.visible()
        }
    }
}