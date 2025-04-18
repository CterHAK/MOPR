package com.example.week5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.week5.data.DatabaseHelper

class EditTaskActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var editTextTask: EditText
    private lateinit var btnSave: Button
    private var oldTask: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)


        dbHelper = DatabaseHelper(this)
        editTextTask = findViewById(R.id.editTextTask)
        btnSave = findViewById(R.id.btnSave)

        oldTask = intent.getStringExtra("taskName") // Lấy dữ liệu từ Intent
        editTextTask.setText(oldTask)

        btnSave.setOnClickListener {
            val newTask = editTextTask.text.toString()
            if (!newTask.isNullOrEmpty() && oldTask != null) {
                dbHelper.updateTask(oldTask!!, newTask) // Cập nhật dữ liệu trong DB

                val resultIntent = Intent()
                resultIntent.putExtra("updatedTask", newTask)
                resultIntent.putExtra("oldTask", oldTask)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
