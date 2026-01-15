package com.example.flashlight

import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var flashSwitch : Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val torch = Torch(this)
        flashSwitch = findViewById<Switch>(R.id.flashSwitch)
        val intent = Intent(this, TorchService::class.java)

        flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                torch.flashOn()
                startService(intent)
            } else {
                torch.flashOff()
                startService(intent)
            }
        }
    }
}