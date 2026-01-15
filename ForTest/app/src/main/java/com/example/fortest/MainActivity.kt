package com.example.fortest

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var myHelper : myDBHelper
    lateinit var edtName : EditText;        lateinit var edtNumber : EditText
    lateinit var edtNameResult : EditText;  lateinit var edtNumResult : EditText
    lateinit var btnInit : Button;          lateinit var btnSelect : Button
    lateinit var btnInsert : Button
    lateinit var sqlDB : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById<EditText>(R.id.editName)
        edtNumber = findViewById<EditText>(R.id.editNumber)
        edtNameResult = findViewById<EditText>(R.id.editNameResult)
        edtNumResult = findViewById<EditText>(R.id.editNumResult)

        btnInit = findViewById<Button>(R.id.btnInit)
        btnInsert = findViewById<Button>(R.id.btnInsert)
        btnSelect = findViewById<Button>(R.id.btnSelect)

        myHelper = myDBHelper(this)

        btnInit.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            myHelper.onUpgrade(sqlDB, 1, 2)
            sqlDB.close()
        }

        btnInsert.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("INSERT INTO groupTest VALUES ( '"
                            + edtName.text.toString() + "', "
                            + edtNumber.text.toString() + ");")
            sqlDB.close()
            Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show()
        }

        btnSelect.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            var cursor : Cursor
            cursor = sqlDB.rawQuery("SELECT * FROM groupTest;", null)

            var strNames = "그룹 이름: " + "\r\n" + "------------" + "\r\n"
            var strNumbers = "인원: " + "\r\n" + "------------" + "\r\n"

            while (cursor.moveToNext()) {
                strNames += cursor.getString(0) + "\r\n"
                strNumbers += cursor.getString(1) + "\r\n"
            }

            edtNameResult.setText(strNames)
            edtNumResult.setText(strNumbers)

            cursor.close()
            sqlDB.close()
        }
    }

    inner class myDBHelper(context : Context) : SQLiteOpenHelper(context, "group08", null, 1) {
        // 1번 파라미터 데이터베이스를 생성하는 액티비티에 대한 정보가 필요함. 메인 액티비티의 내부 클래스로 생성한 거라서 여기서는 this의미의 context를 입력함
        // 2번 파라미터 name = DB 이름
        // 3번 파라미터 커서를 저장하는 매개변수. null 을 입력하면 표준 커서를 사용.
        // 4번 파라미터 데이터베이스 버전에 대한 정보를 의미함. 이후 onUpgrade 메서드를 통해 변경할 수 있음
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE groupTest ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER );")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS groupTest")
            onCreate(db)
        }
    }
}