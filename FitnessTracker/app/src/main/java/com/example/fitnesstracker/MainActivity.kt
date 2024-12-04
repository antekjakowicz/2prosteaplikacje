package com.example.dropdownexample

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnesstracker.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)

        // Znajdź AutoCompleteTextView w widoku
        val dropdownMenu = findViewById<AutoCompleteTextView>(R.id.dropdownMenu)

        // Dane dla dropdown menu
        val options = listOf("Opcja 1", "Opcja 2", "Opcja 3", "Opcja 4")

        // Adapter dla dropdown menu
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, // Standardowy układ Androida
            options
        )

        // Ustaw adapter na AutoCompleteTextView
        dropdownMenu.setAdapter(adapter)

        // Obsługa kliknięcia na opcję
        dropdownMenu.setOnItemClickListener { parent, , position, _ ->
            val selectedOption = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Wybrano: $selectedOption", Toast.LENGTH_SHORT).show()
        }
    }
}