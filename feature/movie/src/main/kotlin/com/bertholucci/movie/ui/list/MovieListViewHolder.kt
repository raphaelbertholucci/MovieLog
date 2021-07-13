package com.bertholucci.movie.ui.list

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.movie.databinding.MovieItemListBinding
import com.bertholucci.movie.model.Movie

class MovieListViewHolder internal constructor(
    itemView: View,
    val onClick: ((Movie) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    private var binding: MovieItemListBinding? = DataBindingUtil.bind(itemView)

    fun bind(item: Movie) {
        binding?.movie = item
        binding?.ivPoster?.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}