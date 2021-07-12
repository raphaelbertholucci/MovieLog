package com.bertholucci.home.ui.top_rated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.model.Movie
import com.bertholucci.home.R

class TopRatedAdapter(private val list: List<Movie> = listOf()) :
    RecyclerView.Adapter<TopRatedViewHolder>() {

    var onClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopRatedViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_rated, parent, false),
            onClick = onClick
        )

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}