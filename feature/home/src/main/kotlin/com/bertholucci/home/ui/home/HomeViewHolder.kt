package com.bertholucci.home.ui.home

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.home.databinding.ItemHomeBinding
import com.bertholucci.core.model.Movie

class HomeViewHolder internal constructor(itemView: View, val onClick: ((Movie) -> Unit)? = null) :
    RecyclerView.ViewHolder(itemView) {

    private var binding = DataBindingUtil.bind(itemView) as ItemHomeBinding?

    fun bind(item: Movie) {
        binding?.movie = item
        binding?.ivPoster?.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}