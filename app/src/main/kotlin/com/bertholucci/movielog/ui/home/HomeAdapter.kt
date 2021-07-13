package com.bertholucci.movielog.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.movie.model.Movie
import com.bertholucci.movielog.R

class HomeAdapter(private val list: List<Movie> = listOf()) :
    RecyclerView.Adapter<HomeViewHolder>() {

    var onClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false),
            onClick = onClick
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}