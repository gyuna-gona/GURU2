package com.example.studentmanagement

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PersonnelInfo : AppCompatActivity() {
    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var tvName : TextView;     lateinit var tvGender : TextView
    lateinit var tvAge : TextView;      lateinit var tvTel : TextView

    lateinit var str_name : String
    lateinit var str_gender : String
    var age : Int = 0
    lateinit var str_tel : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_info)

        tvName = findViewById<TextView>(R.id.name)
        tvGender = findViewById<TextView>(R.id.gender)
        tvAge = findViewById<TextView>(R.id.age)
        tvTel = findViewById<TextView>(R.id.tel)

        val intent = intent
        str_name = intent.getStringExtra("intent_name").toString()

        dbManager = DBManager(this, "personnel", null, 1)
        sqlitedb = dbManager.writableDatabase

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM personnel WHERE name='" + str_name + "';", null)

        if (cursor.moveToNext()) {
            str_gender = cursor.getString(cursor.getColumnIndex("gender")).toString()
            age = cursor.getInt(cursor.getColumnIndex("age"))
            str_tel = cursor.getString(cursor.getColumnIndex("tel")).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        tvName.text = str_name
        tvGender.text = str_gender
        tvAge.text = "" + age
        tvTel.text = str_tel + "\n"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_list -> {
                val intent = Intent(this, PersonnelList::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_reg -> {
                val intent = Intent(this, PersonnelReg::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_remove -> {
                dbManager = DBManager(this, "personnel", null, 1)
                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("DELETE FROM personnel WHERE name='" + str_name + "';")
                sqlitedb.close()
                dbManager.close()

                val intent = Intent(this, PersonnelList::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}