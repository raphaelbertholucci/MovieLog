package com.bertholucci.movielog.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.movie.model.Movie
import com.bertholucci.movielog.R

class FavoritesAdapter(private val list: List<Movie>) :
    RecyclerView.Adapter<FavoritesViewHolder>() {

    var onClick: ((Movie) -> Unit)? = null
    var imageView: ((View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorites, parent, false),
            onClick = onClick
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(list[position])
    }
}