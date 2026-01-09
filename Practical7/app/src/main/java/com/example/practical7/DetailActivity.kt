package com.example.practical7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvTitle = findViewById<TextView>(R.id.tvCourseTitle)
        val btnShare = findViewById<Button>(R.id.btnShare)

        // 1. Get Data from previous Activity
        val courseName = intent.getStringExtra("COURSE_NAME")
        tvTitle.text = courseName

        // 2. Event Listener for Share Button
        btnShare.setOnClickListener {
            // 3. Implicit Intent: Share data to other apps
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hey! Check out this course: $courseName")
                type = "text/plain"
            }
            // Start the chooser dialog
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}
