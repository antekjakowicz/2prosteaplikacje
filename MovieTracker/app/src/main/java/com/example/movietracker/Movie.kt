package com.example.movietracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Movie(
    val title: String,
    val genre: String,
    val opinion: String,
    val rating: Int,
    val isWatched: Boolean
)

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val onMarkWatched: (Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val markWatchedButton: Button = itemView.findViewById(R.id.markWatchedButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MovieViewHolder(view)
    }
}
