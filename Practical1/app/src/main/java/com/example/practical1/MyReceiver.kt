package com.example.practical1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            Toast.makeText(context, "Broadcast: Airplane Mode Changed", Toast.LENGTH_LONG).show()
        }
    }
}