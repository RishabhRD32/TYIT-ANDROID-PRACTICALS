package com.example.practical3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log

class SecondActivity : AppCompatActivity() {
    private val TAG = "ActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(TAG, "SecondActivity: onCreate")

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            // Finish this activity to go back to the previous one in the stack
            finish()
        }
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "SecondActivity: onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "SecondActivity: onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "SecondActivity: onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "SecondActivity: onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "SecondActivity: onDestroy") }
}
