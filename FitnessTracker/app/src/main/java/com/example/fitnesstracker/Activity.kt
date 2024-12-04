package com.example.movietracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Activity(
    val title: String,
    val genre: String,
    val rating: Int,
    val opinion: String
)

class MovieAdapter(
    private val movies: MutableList<Movie>,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val opinionTextView: TextView = itemView.findViewById(R.id.opinionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title
        holder.genreTextView.text = movie.genre
        holder.ratingTextView.text = "Ocena: ${movie.rating}"
        holder.opinionTextView.text = "Opinia: ${movie.opinion}"

        Log.d("MovieAdapter", "Wyświetlany film: ${movie.title}")
    }

    override fun getItemCount(): Int = movies.size
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie) {
        itemView.findViewById<TextView>(R.id.titleEditText).text = movie.title
    }
}
