package com.example.practical11a

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // Variables for Media
    private var mediaPlayer: MediaPlayer? = null

    // Variables for Telephony
    private lateinit var etPhone: EditText
    private lateinit var tvState: TextView
    private lateinit var telephonyManager: TelephonyManager

    // Request Codes
    private val PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- PART 11A: MEDIA API ---
        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnStop = findViewById<Button>(R.id.btnStop)

        btnPlay.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
                mediaPlayer?.start()
                Toast.makeText(this, "Music Started", Toast.LENGTH_SHORT).show()
            }
        }

        btnStop.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show()
            }
        }

        // --- PART 11B: SECURITY & PERMISSIONS (Telephony) ---
        etPhone = findViewById(R.id.etPhoneNumber)
        tvState = findViewById(R.id.tvState)
        val btnCall = findViewById<Button>(R.id.btnCall)

        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // 1. Check Permissions immediately when app starts to fix the Crash
        checkPermissionsAndListen()

        // 2. Button Logic
        btnCall.setOnClickListener {
            val number = etPhone.text.toString()
            if (number.isNotEmpty()) {
                makePhoneCall(number)
            } else {
                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // --- HELPER FUNCTIONS ---

    private fun checkPermissionsAndListen() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            // Request BOTH permissions at once
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE),
                PERMISSION_CODE
            )
        } else {
            // Permissions already granted, safe to listen
            startListening()
        }
    }

    private fun startListening() {
        val phoneListener = object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                when (state) {
                    TelephonyManager.CALL_STATE_IDLE -> tvState.text = "Phone State: Idle"
                    TelephonyManager.CALL_STATE_RINGING -> tvState.text = "Phone State: Ringing..."
                    TelephonyManager.CALL_STATE_OFFHOOK -> tvState.text = "Phone State: On Call"
                }
            }
        }
        // This line causes the crash if permission is missing, so we wrap it
        try {
            telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE)
        } catch (e: SecurityException) {
            Toast.makeText(this, "Permission missing for Listener", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makePhoneCall(number: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        } else {
            Toast.makeText(this, "Permission not granted yet", Toast.LENGTH_SHORT).show()
            checkPermissionsAndListen()
        }
    }

    // Handle User Response to Permission Request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // User clicked "Allow", now we can start listening safely
                startListening()
            } else {
                Toast.makeText(this, "Permission Denied. App functions Limited.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}