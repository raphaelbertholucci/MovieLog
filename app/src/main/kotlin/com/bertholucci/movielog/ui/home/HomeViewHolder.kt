package com.bertholucci.movielog.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.extensions.loadFromUrl
import com.bertholucci.movie.model.Movie
import com.bertholucci.movielog.databinding.ItemHomeBinding

class HomeViewHolder internal constructor(itemView: View, val onClick: ((Movie) -> Unit)? = null) :
    RecyclerView.ViewHolder(itemView) {

    private var binding = ItemHomeBinding.bind(itemView)

    fun bind(item: Movie) {
        binding.tvTitle.text = item.title
        binding.ivPoster.loadFromUrl(item.posterPath)

        itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}