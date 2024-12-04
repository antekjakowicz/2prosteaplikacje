package com.example.movietracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R

data class Activity(
    val distance: String,
    val time: String,
    val calories: String,
    val intensity: String,
    val activity: String
)

class ActivityAdapter(
    private val activities: MutableList<Activity>
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeOfActivityTextView: TextView = itemView.findViewById(R.id.typeOfActivityTextView)
        val distanceTextView: TextView = itemView.findViewById(R.id.distanceTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val caloriesTextView: TextView = itemView.findViewById(R.id.caloriesTextView)
        val intensityTextView: TextView = itemView.findViewById(R.id.intensivityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.typeOfActivityTextView.text = activity.activity
        holder.distanceTextView.text = "Dystans: ${activity.distance} m"
        holder.timeTextView.text = "Czas: ${activity.time} min"
        holder.caloriesTextView.text = "Spalone kalorie: ${activity.calories}"
        holder.intensityTextView.text = "Intensywność: ${activity.intensity}"


    }

    override fun getItemCount(): Int = activities.size

    fun addActivity(activity: Activity) {
        activities.add(activity)
        notifyItemInserted(activities.size - 1)
    }
}