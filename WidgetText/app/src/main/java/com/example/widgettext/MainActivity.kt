package com.example.widgettext

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv1 : TextView
        var tv2 : TextView
        var tv3 : TextView

        tv1 = findViewById<TextView>(R.id.textView1)
        tv2 = findViewById<TextView>(R.id.textView2)
        tv3 = findViewById<TextView>(R.id.textView3)

        tv1.setText("안녕하세요! TextView 1을 수정했습니다.")
        tv1.setTextColor(Color.RED)

        tv2.setTextSize(30.0f)
        tv2.setTypeface(android.graphics.Typeface.SERIF,
                            android.graphics.Typeface.BOLD_ITALIC )

        tv3.setText("가나다라마바사아자차카타파하가나다라마바사아자차카타파하" +
                "가나다라마바사아자차카타파하가나다라마바사아자차카타파하")
        tv3.setSingleLine()
    }
}