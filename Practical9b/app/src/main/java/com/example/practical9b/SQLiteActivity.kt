package com.example.practical9b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SQLiteActivity : AppCompatActivity() {

    lateinit var dbHelper: UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        dbHelper = UserDBHelper(this)

        val etId = findViewById<EditText>(R.id.etUserId)
        val etName = findViewById<EditText>(R.id.etUserName)
        val etAge = findViewById<EditText>(R.id.etUserAge)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // 1. Add User
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val isInserted = dbHelper.insertUser(
                etId.text.toString(),
                etName.text.toString(),
                etAge.text.toString()
            )
            if (isInserted) {
                Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show()
                etId.text.clear(); etName.text.clear(); etAge.text.clear()
            } else {
                Toast.makeText(this, "Error: ID might already exist", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Delete User
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val rows = dbHelper.deleteUser(etId.text.toString())
            if (rows > 0) {
                Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show()
                etId.text.clear()
            } else {
                Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. View All Users
        findViewById<Button>(R.id.btnView).setOnClickListener {
            val cursor = dbHelper.readAllUsers()
            if (cursor.count == 0) {
                tvResult.text = "No records found."
                return@setOnClickListener
            }

            val buffer = StringBuffer()
            while (cursor.moveToNext()) {
                buffer.append("ID: ${cursor.getString(0)}\n")
                buffer.append("Name: ${cursor.getString(1)}\n")
                buffer.append("Age: ${cursor.getString(2)}\n\n")
            }
            tvResult.text = buffer.toString()
        }
    }
}