package com.example.practical9

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SharedPrefActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)

        val etNote = findViewById<EditText>(R.id.etNote)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val tvSaved = findViewById<TextView>(R.id.tvSavedData)

        // 1. Initialize SharedPreferences
        // "MyPrefs" is the file name, MODE_PRIVATE means only this app can access it
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // 2. Load saved data on startup
        val savedName = sharedPreferences.getString("USER_NAME", "None")
        tvSaved.text = "Saved Name: $savedName"

        // 3. Save Data
        btnSave.setOnClickListener {
            val name = etNote.text.toString()
            if (name.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("USER_NAME", name)
                editor.apply() // Asynchronous save

                tvSaved.text = "Saved Name: $name"
                Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Clear Data
        btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            tvSaved.text = "Saved Name: None"
            etNote.text.clear()
            Toast.makeText(this, "Data Cleared", Toast.LENGTH_SHORT).show()
        }
    }
}