package com.example.constraintlayout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var helloText : TextView
    lateinit var clickButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloText = findViewById(R.id.textView)
        clickButton = findViewById(R.id.clickButton)

        clickButton.setOnClickListener {
            helloText.text = "버튼을 눌러주세요"
        }
    }
}