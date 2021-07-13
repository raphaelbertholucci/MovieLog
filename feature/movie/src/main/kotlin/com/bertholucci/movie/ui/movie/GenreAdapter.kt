package com.bertholucci.movie.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.movie.R
import com.bertholucci.movie.model.Genre
import com.bertholucci.movie.databinding.ItemGenreBinding

class GenreAdapter(private val list: List<Genre> = listOf()) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre, parent, false)
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class GenreViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private var binding = DataBindingUtil.bind(itemView) as ItemGenreBinding?

        fun bind(item: Genre) {
            binding?.tvGenre?.text = item.name
        }
    }
}