package com.example.fitnesstracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietracker.Activity
import com.example.movietracker.ActivityAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ActivityAdapter
    private val activityList = mutableListOf<Activity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ActivityAdapter(activityList)
        recyclerView.adapter = adapter
        Log.d("MainActivity", "Adapter ustawiony: ${adapter}")

        val intensitySeekBar = findViewById<SeekBar>(R.id.intensitySeekBar)
        val intensityTextView = findViewById<TextView>(R.id.intensityTextView)

        intensitySeekBar.progress = 0

        intensitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                intensityTextView.text = "Intensywność: ${progress + 1}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            saveActivity()
        }

        loadActivities()
    }

    private fun saveActivity() {
        val distanceString = findViewById<EditText>(R.id.distanceEditText).text.toString()
        val distance = distanceString.toIntOrNull() ?: 0

        val timeString = findViewById<EditText>(R.id.timeEditText).text.toString()
        val time = timeString.toIntOrNull() ?: 0

        val caloriesString = findViewById<EditText>(R.id.caloriesEditText).text.toString()
        val calories = caloriesString.toIntOrNull() ?: 0

        val activityType = when {
            findViewById<RadioButton>(R.id.walkRadioButton).isChecked -> "Spacer"
            findViewById<RadioButton>(R.id.runRadioButton).isChecked -> "Bieg"
            findViewById<RadioButton>(R.id.swimRadioButton).isChecked -> "Pływanie"
            else -> "Brak"
        }

        val intensity = findViewById<SeekBar>(R.id.intensitySeekBar).progress + 1

        if (activityType != "Brak") {
            val newActivity = Activity(
                distance = distance.toString(),
                time = time.toString(),
                calories = calories.toString(),
                intensity = intensity.toString(),
                activity = activityType
            )


            activityList.add(newActivity)
            adapter.notifyDataSetChanged()
            saveActivities()
        } else {
            Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveActivities() {
        val gson = Gson()
        val json = gson.toJson(activityList)
        val file = File(filesDir, "activities.json")
        file.writeText(json)
    }

    private fun loadActivities() {
        val file = File(filesDir, "activities.json")
        if (file.exists()) {
            val gson = Gson()
            val json = file.readText()
            val type = object : TypeToken<List<Activity>>() {}.type
            val loadedActivities: List<Activity> = gson.fromJson(json, type)
            activityList.clear()
            activityList.addAll(loadedActivities)
            adapter.notifyDataSetChanged()
        }
    }
}
