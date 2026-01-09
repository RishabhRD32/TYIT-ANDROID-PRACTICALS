package com.example.practical5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUser = findViewById<EditText>(R.id.etLoginUsername)
        val etPass = findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLoginUser)

        btnLogin.setOnClickListener {
            val inputUser = etUser.text.toString()
            val inputPass = etPass.text.toString()

            // Fetch Saved Data
            val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val savedUser = sharedPref.getString("USERNAME", null)
            val savedPass = sharedPref.getString("PASSWORD", null)

            if (savedUser == null) {
                Toast.makeText(this, "No user found. Please Register first.", Toast.LENGTH_LONG).show()
            } else if (inputUser == savedUser && inputPass == savedPass) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}