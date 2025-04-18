package com.example.week5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.week5.data.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView

    private val ADD_TASK_REQUEST = 1 // Mã request
    private val EDIT_TASK_REQUEST = 2 // Mã request cho chỉnh sửa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Khởi tạo Adapter một lần duy nhất
        adapter = TaskAdapter(
            dbHelper.getAllTasks().toMutableList(),
            dbHelper,
            { loadTasks() }, // Tham số refresh
            { task ->  // Tham số onEditTask
                val intent = Intent(this, EditTaskActivity::class.java)
                intent.putExtra("taskName", task)
                startActivityForResult(intent, EDIT_TASK_REQUEST)
            }
        )
        recyclerView.adapter = adapter


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST) // Mở activity để thêm task
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val newTask = data?.getStringExtra("newTask")
            if (newTask != null) {
                adapter.addTask(newTask) // Cập nhật RecyclerView ngay khi thêm mới
            }
        }
        if (requestCode == EDIT_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val updatedTask = data?.getStringExtra("updatedTask")
            val oldTask = data?.getStringExtra("oldTask")
            if (updatedTask != null && oldTask != null) {
                adapter.updateEditedTask(oldTask, updatedTask)
            }
        }
    }
    private fun loadTasks() {
        val tasks = dbHelper.getAllTasks()
        adapter.updateTasks(tasks) // Cập nhật RecyclerView với danh sách mới
    }


}
