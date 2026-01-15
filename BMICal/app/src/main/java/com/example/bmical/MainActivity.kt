package com.example.bmical

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var resultButton : Button
    lateinit var nameEditText : EditText
    lateinit var heightEditText : EditText
    lateinit var weightEditText : EditText

    private fun saveData(height: Int, weight: Int, name: String) {
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putInt("KEY_HEIGHT", heightEditText.text.toString().toInt()).apply()
        editor.putInt("KEY_WEIGHT", weightEditText.text.toString().toInt()).apply()
        editor.putString("KEY_NAME", nameEditText.text.toString()).apply()
    }

    // 입력한 내용이 result 액티비티로 넘어갔다가 뒤로 가기로 돌아와도 그대로 남아있도록 하는 코드
    private fun loadData() {
        var pref = this.getPreferences(0)
        var height = pref.getInt("KEY_HEIGHT", 0)
        var weight = pref.getInt("KEY_WEIGHT", 0)
        var name = pref.getString("KEY_NAME", null)

        if (height != 0 && weight != 0) {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
            nameEditText.setText(name.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultButton = findViewById<Button>(R.id.resultButton)

        heightEditText = findViewById<EditText>(R.id.heightEditText)
        weightEditText = findViewById<EditText>(R.id.weightEditText)
        nameEditText = findViewById<EditText>(R.id.nameEditText)

        loadData()

        resultButton.setOnClickListener {
            saveData(heightEditText.text.toString().toInt(),
                    weightEditText.text.toString().toInt(),
                    nameEditText.text.toString())

            var intent = Intent(this, ReResultActivity::class.java)
            intent.putExtra("height", heightEditText.text.toString())
            intent.putExtra("weight", weightEditText.text.toString())
            intent.putExtra("name", nameEditText.text.toString())

            startActivity(intent)
        }
    }
}