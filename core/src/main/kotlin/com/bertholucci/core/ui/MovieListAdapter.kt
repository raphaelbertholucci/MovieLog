package com.bertholucci.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.core.R
import com.bertholucci.core.model.Movie

class MovieListAdapter(private val list: MutableList<Movie> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClick: ((Movie) -> Unit)? = null

    var isLoading = false
    var isFullyLoaded = false
    private val viewTypeItem = 0
    private val viewTypeLoading = 1

    fun updateList(movieList: List<Movie>) {
        list.addAll(movieList)
        notifyDataSetChanged()
    }

    fun setList(movieList: List<Movie>) {
        list.clear()
        list.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == viewTypeItem) {
            MovieListViewHolder(
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item_list, parent, false),
                onClick = onClick
            )
        } else {
            LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieListViewHolder) {
            holder.bind(list[position])
        } else if (holder is LoadingViewHolder) {
            holder.bind(isFullyLoaded)
        }
    }

    override fun getItemCount(): Int =
        if (list.isNotEmpty()) list.size + 1
        else 0

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) viewTypeLoading else viewTypeItem
    }

    override fun getItemId(position: Int): Long {
        return if (getItemViewType(position) == viewTypeItem) position.toLong()
        else -1
    }
}