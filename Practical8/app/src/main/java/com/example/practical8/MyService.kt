package com.example.practical8

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast

class MyService : Service() {

    private lateinit var player: MediaPlayer

    // 1. Service Created
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // 2. Service Started
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Load default ringtone
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = true
        player.start()

        Toast.makeText(this, "Service Started: Ringtone Playing", Toast.LENGTH_SHORT).show()

        // If system kills service, recreate it
        return START_STICKY
    }

    // 3. Service Stopped
    override fun onDestroy() {
        super.onDestroy()
        if (this::player.isInitialized) {
            player.stop()
            player.release()
        }
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show()
    }
}