package com.bertholucci.home.ui.favorites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.core.model.Movie
import com.bertholucci.home.databinding.ItemFavoritesBinding

class FavoritesViewHolder internal constructor(
    itemView: View,
    val onClick: ((Movie) -> Unit)? = null
) :
    RecyclerView.ViewHolder(itemView) {

    private var binding = ItemFavoritesBinding.bind(itemView)

    fun bind(item: Movie) {
        binding.tvTitle.text = item.title
        binding.ivPoster.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}