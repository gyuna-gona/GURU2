package com.example.stopwatch

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1


    lateinit var fab : FloatingActionButton
    lateinit var resetFab : FloatingActionButton
    lateinit var secTextView : TextView
    lateinit var miliTextView: TextView
    lateinit var labButton : Button
    lateinit var lapLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById<FloatingActionButton>(R.id.fab)
        resetFab = findViewById<FloatingActionButton>(R.id.resetFab)
        secTextView = findViewById<TextView>(R.id.secTextView)
        miliTextView = findViewById<TextView>(R.id.miliTextView)
        labButton = findViewById<Button>(R.id.labButton)

        fab.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        labButton.setOnClickListener {
            recordLapTime()
        }

        resetFab.setOnClickListener {
            reset()
        }
    }

    private fun start() {
        fab.setImageResource(R.drawable.pause)
        timerTask = timer(period=10) {
            time++
            val sec = time / 100
            val mili = time % 100
            runOnUiThread {
                secTextView.text = "$sec"
                miliTextView.text = "$mili"
            }
        }
    }

    private fun pause() {
        fab.setImageResource(R.drawable.play_arrow)
        timerTask?.cancel()
    }

    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        lapLayout.addView(textView, 0)
        lap++
    }

    private fun reset() {
        timerTask?.cancel()

        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.play_arrow)
        secTextView.text = "0"
        miliTextView.text = "00"

        lapLayout.removeAllViews()
        lap = 1
    }
}