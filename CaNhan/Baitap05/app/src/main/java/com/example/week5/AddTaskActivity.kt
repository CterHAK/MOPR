package com.example.week5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.week5.data.DatabaseHelper
import com.example.week5.model.User


class AddTaskActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        dbHelper = DatabaseHelper(this)
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val taskName = editTextTask.text.toString()
            if (taskName.isNotEmpty()) {
                val newUser = User(0, taskName, 0)
                dbHelper.addUser(newUser)

                // Gửi lại dữ liệu task mới
                val resultIntent = Intent()
                resultIntent.putExtra("newTask", taskName)
                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            }
        }




    }
}
