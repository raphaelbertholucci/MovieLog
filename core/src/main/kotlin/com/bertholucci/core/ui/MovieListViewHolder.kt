package com.bertholucci.core.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.databinding.ItemMovieListBinding
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.core.model.Movie

class MovieListViewHolder internal constructor(
    itemView: View,
    val onClick: ((Movie) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemMovieListBinding? = DataBindingUtil.bind(itemView)

    fun bind(item: Movie) {
        binding?.movie = item
        binding?.ivPoster?.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}