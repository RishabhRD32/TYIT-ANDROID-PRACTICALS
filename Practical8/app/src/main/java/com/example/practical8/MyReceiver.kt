package com.example.practical8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)
            val msg = if (isAirplaneModeOn) "Airplane Mode ON" else "Airplane Mode OFF"
            Toast.makeText(context, "Broadcast Received: $msg", Toast.LENGTH_LONG).show()
        }
    }
}