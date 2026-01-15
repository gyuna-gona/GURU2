package com.example.compoundbtn

import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
//    lateinit var checkApple : CheckBox
//    lateinit var checkOrange : CheckBox
//    lateinit var checkBanana : CheckBox
//
//    var listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
//        if (isChecked) {
//            when(buttonView.id) {
//                R.id.checkApple -> Toast.makeText(applicationContext, "사과", Toast.LENGTH_SHORT).show()
//                R.id.checkOrange -> Toast.makeText(applicationContext, "오렌지", Toast.LENGTH_SHORT).show()
//                R.id.checkBanana -> Toast.makeText(applicationContext, "바나나", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    lateinit var radioGroup : RadioGroup
    lateinit var radioApple : RadioButton
    lateinit var radioOrange : RadioButton
    lateinit var radioBanana : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        checkApple = findViewById<CheckBox>(R.id.checkApple)
//        checkOrange = findViewById<CheckBox>(R.id.checkOrange)
//        checkBanana = findViewById<CheckBox>(R.id.checkBanana)
//
//        checkApple.setOnCheckedChangeListener(listener)
//        checkOrange.setOnCheckedChangeListener(listener)
//        checkBanana.setOnCheckedChangeListener(listener)

        radioGroup = findViewById<RadioGroup>(R.id.rGroup1)

        radioApple = findViewById<RadioButton>(R.id.radioApple)
        radioOrange = findViewById<RadioButton>(R.id.radioOrange)
        radioBanana = findViewById<RadioButton>(R.id.radioBanana)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioApple -> Toast.makeText(applicationContext, "사과", Toast.LENGTH_SHORT).show()
                R.id.radioOrange -> Toast.makeText(applicationContext, "오렌지", Toast.LENGTH_SHORT).show()
                R.id.radioBanana -> Toast.makeText(applicationContext, "바나나", Toast.LENGTH_SHORT).show()
            }
        }
    }
}