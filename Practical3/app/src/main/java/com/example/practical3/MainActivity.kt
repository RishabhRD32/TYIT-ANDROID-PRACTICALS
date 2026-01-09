package com.example.practical3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val TAG = "ActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate called")

        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            // Explicit Intent to start SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart called") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume called") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause called") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop called") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart called") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy called") }
}