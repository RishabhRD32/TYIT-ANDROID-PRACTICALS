package com.example.practical6

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button and set a click listener to show the dialog
        val btnShowDialog = findViewById<Button>(R.id.btnShowDialog)
        btnShowDialog.setOnClickListener {
            showCustomAlertDialog()
        }
    }

    // ----------------------------------------------------
    // MENU LOGIC
    // ----------------------------------------------------

    // 1. Inflate the menu resource (xml) into the Action Bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // 2. Handle clicks on menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_exit -> {
                // Show a confirmation dialog before exiting
                showExitConfirmationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ----------------------------------------------------
    // DIALOG LOGIC
    // ----------------------------------------------------

    // Function to show a simple Alert Dialog
    private fun showCustomAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("This is a simple Alert Dialog.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        // Positive Button (Yes/OK)
        builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, "You clicked OK", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        // Negative Button (No/Cancel)
        builder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, "You clicked Cancel", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        // Create and Show
        val alertDialog = builder.create()
        alertDialog.show()
    }

    // Function to show Exit Confirmation
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit App")
        builder.setMessage("Are you sure you want to close the application?")

        builder.setPositiveButton("Yes") { _, _ ->
            finish() // Closes the app
            System.exit(0)
        }

        builder.setNegativeButton("No", null) // Do nothing

        builder.show()
    }
}
