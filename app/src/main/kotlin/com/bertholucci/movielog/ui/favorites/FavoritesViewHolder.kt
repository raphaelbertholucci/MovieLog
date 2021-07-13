package com.bertholucci.movielog.ui.favorites

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.movie.model.Movie
import com.bertholucci.movielog.databinding.ItemFavoritesBinding

class FavoritesViewHolder internal constructor(
    itemView: View,
    val onClick: ((Movie) -> Unit)? = null
) :
    RecyclerView.ViewHolder(itemView) {

    private var binding: ItemFavoritesBinding? = DataBindingUtil.bind(itemView)

    fun bind(item: Movie) {
        binding?.movie = item

        binding?.ivPoster?.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}