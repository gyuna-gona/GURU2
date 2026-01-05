package com.example.bmical

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class ReResultActivity : AppCompatActivity() {
    lateinit var resultTextView : TextView
    lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_result)

        resultTextView = findViewById<TextView>(R.id.resultTextView)
        imageView = findViewById<ImageView>(R.id.imageView)

        var height = intent.getStringExtra("height")!!.toInt()
        var weight = intent.getStringExtra("weight")!!.toInt()
        var name = intent.getStringExtra("name").toString()

        //BMI 계산하기
        var bmi = weight / Math.pow(height/100.0, 2.0)

        //BMI 결과에 따른 결과 텍스트 출력
        when {
            bmi >= 35 -> resultTextView.text = "고도 비만"
            bmi >= 30 -> resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultTextView.text = "과체중"
            bmi >= 18.5 -> resultTextView.text = "정상 체중"
            else -> resultTextView.text = "저체중"
        }

        //BMI 결과에 따라 이미지 출력
        when {
            bmi >= 23 -> imageView.setImageResource(R.drawable.very_dissatisfied)
            bmi > 18.5 -> imageView.setImageResource(R.drawable.satisfied)
            else -> imageView.setImageResource(R.drawable.dissatisfied)
        }

        Toast.makeText(this, "$name : ${bmi.roundToInt()}", Toast.LENGTH_SHORT).show()
    }
}