package com.example.practical10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnStart: Button

    // 1. Create a Handler attached to the Main (UI) Looper
    // This allows the background thread to send work back to the UI thread.
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        progressBar = findViewById(R.id.progressBar)
        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            startBackgroundTask()
        }
    }

    private fun startBackgroundTask() {
        // Disable button so user doesn't click it twice
        btnStart.isEnabled = false
        tvStatus.text = "Status: Working..."
        progressBar.progress = 0

        // 2. Create a new Thread to run the heavy task
        Thread {
            // Simulate work (e.g., downloading a file)
            for (i in 1..10) {

                // Simulate a delay of 1 second per step
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                val progress = i * 10

                // 3. Use Handler to update UI
                // We cannot update UI directly from this Thread, we must post to Handler
                handler.post {
                    progressBar.progress = progress
                    tvStatus.text = "Status: $progress%"

                    if (progress == 100) {
                        tvStatus.text = "Status: Completed!"
                        btnStart.isEnabled = true
                        Toast.makeText(this, "Task Finished", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }.start() // Don't forget to start the thread!
    }
}