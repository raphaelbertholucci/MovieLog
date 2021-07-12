package com.bertholucci.home.ui.top_rated

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.core.model.Movie
import com.bertholucci.home.databinding.ItemTopRatedBinding

class TopRatedViewHolder internal constructor(
    itemView: View,
    val onClick: ((Movie) -> Unit)? = null
) :
    RecyclerView.ViewHolder(itemView) {

    private var binding = DataBindingUtil.bind(itemView) as ItemTopRatedBinding?

    fun bind(item: Movie) {
        binding?.movie = item
        binding?.ivPoster?.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}