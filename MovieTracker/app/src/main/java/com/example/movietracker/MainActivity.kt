package com.example.movietracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(movieList)
        recyclerView.adapter = adapter
        Log.d("MainActivity", "Adapter ustawiony: ${adapter}")



        var seekBar = findViewById<SeekBar>(R.id.seekBar)
        val myRatingTextView = findViewById<TextView>(R.id.myRatingTextView)

        seekBar.progress = 0

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                myRatingTextView.text = ("Twoja ocena: "+(progress+1).toString())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })


        findViewById<Button>(R.id.addButton).setOnClickListener{
            addMovie()
        }

        loadMovies()
    }

    private fun addMovie() {
        val title = findViewById<EditText>(R.id.titleEditText).text.toString()
        val genre = if(findViewById<RadioButton>(R.id.radioDrama).isChecked) "Dramat" else "Komedia"
        val rating = findViewById<SeekBar>(R.id.seekBar).progress + 1
        val opinion = findViewById<EditText>(R.id.opinionEditText).text.toString()

        if (title.isNotEmpty()) {
            val exists = movieList.any { it.title == title }
            if (exists) {
                Toast.makeText(this, "Film o tym tytule już istnieje!", Toast.LENGTH_SHORT).show()
            } else {
                val newMovie = Movie(title, genre, rating, opinion)
                movieList.add(newMovie)
                adapter.notifyDataSetChanged()
                saveMovies()
            }
        } else {
            Toast.makeText(this, "Wpisz tytuł", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveMovies() {
        val gson = Gson()
        val json = gson.toJson(movieList)
        val file = File(filesDir, "movies.json")
        file.writeText(json)
    }

    private fun loadMovies(){
        val file = File(filesDir, "movies.json")
        if (file.exists()){
            val gson = Gson()
            val json = file.readText()
            val type = object : TypeToken<List<Movie>>() {}.type
            val loadedMovies: List<Movie> = gson.fromJson(json, type)
            movieList.clear()
            movieList.addAll(loadedMovies)
            adapter.notifyDataSetChanged()
        }
    }
}