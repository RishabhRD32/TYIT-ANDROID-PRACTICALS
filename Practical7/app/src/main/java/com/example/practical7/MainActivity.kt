package com.example.practical7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Data Source (Array)
        val courses = arrayOf("Android Development", "Python Programming", "Java Core", "Web Development", "Data Science")

        // 2. Adapter (Bridges Data -> ListView)
        // Using a standard Android layout (simple_list_item_1) for rows
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courses)

        // 3. Bind Adapter to ListView
        val listView = findViewById<ListView>(R.id.listViewCourses)
        listView.adapter = adapter

        // 4. Event Listener (OnItemClick)
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCourse = courses[position]
            Toast.makeText(this, "Clicked: $selectedCourse", Toast.LENGTH_SHORT).show()

            // 5. Explicit Intent: Navigate to DetailActivity
            val intent = Intent(this, DetailActivity::class.java)
            // Pass data to the next activity
            intent.putExtra("COURSE_NAME", selectedCourse)
            startActivity(intent)
        }
    }
}
