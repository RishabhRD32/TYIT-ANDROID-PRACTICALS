package com.example.practical5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUser = findViewById<EditText>(R.id.etRegUsername)
        val etPass = findViewById<EditText>(R.id.etRegPassword)
        val btnReg = findViewById<Button>(R.id.btnRegisterUser)

        btnReg.setOnClickListener {
            val username = etUser.text.toString()
            val password = etPass.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save to SharedPreferences
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("USERNAME", username)
                editor.putString("PASSWORD", password)
                editor.apply()

                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()

                // Go to Login Page
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
