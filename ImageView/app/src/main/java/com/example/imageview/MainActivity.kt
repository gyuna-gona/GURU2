package com.example.imageview

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var text1 : TextView;      lateinit var text2 : TextView
    lateinit var chkAgree : CheckBox

    lateinit var rGroup1 : RadioGroup;  lateinit var rdoDog : RadioButton
    lateinit var rdoCat : RadioButton;  lateinit var rdoRabbit : RadioButton

    lateinit var btnOk : Button;        lateinit var imgPet : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "동물 사진 보기"

        text1 = findViewById<TextView>(R.id.text1)
        chkAgree = findViewById<CheckBox>(R.id.chkAgree)

        text2 = findViewById<TextView>(R.id.text2)
        rGroup1 = findViewById<RadioGroup>(R.id.rdoGroup1)
        rdoDog = findViewById<RadioButton>(R.id.rdoDog)
        rdoCat = findViewById<RadioButton>(R.id.rdoCat)
        rdoRabbit = findViewById<RadioButton>(R.id.rdoRabbit)

        btnOk = findViewById<Button>(R.id.btnOk)
        imgPet = findViewById(R.id.imgPet)

        chkAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            if(chkAgree.isChecked == true) {
                text2.visibility = android.view.View.VISIBLE
                rGroup1.visibility = android.view.View.VISIBLE
                btnOk.visibility = android.view.View.VISIBLE
                imgPet.visibility = android.view.View.VISIBLE
            }

            else {
                text2.visibility = android.view.View.INVISIBLE
                rGroup1.visibility = android.view.View.INVISIBLE
                btnOk.visibility = android.view.View.INVISIBLE
                imgPet.visibility = android.view.View.INVISIBLE
            }
        }

        btnOk.setOnClickListener {
            when(rGroup1.checkedRadioButtonId) {
                R.id.rdoDog -> imgPet.setImageResource(R.drawable.dog)
                R.id.rdoCat -> imgPet.setImageResource(R.drawable.cat)
                R.id.rdoRabbit -> imgPet.setImageResource(R.drawable.rabbit)

                else -> Toast.makeText(applicationContext, "동물 먼저 선택하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}