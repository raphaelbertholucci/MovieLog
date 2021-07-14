package com.bertholucci.core.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.databinding.ItemLoadingBinding

class LoadingViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var binding = ItemLoadingBinding.bind(itemView)

    fun bind(isFullyLoaded: Boolean) {
        binding.loading.isVisible = isFullyLoaded.not()
        binding.tvFullyLoaded.isVisible = isFullyLoaded
    }
}