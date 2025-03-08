package com.example.week5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week5.R
import com.example.week5.data.DatabaseHelper

class TaskAdapter(
    private var tasks: MutableList<String>,
    private val dbHelper: DatabaseHelper,
    private val refresh: () -> Unit,
    private val onEditTask: (String) -> Unit // Thêm callback để chỉnh sửa
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTaskName: TextView = itemView.findViewById(R.id.tvTaskName)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.tvTaskName.text = task

        holder.btnEdit.setOnClickListener {
            onEditTask(task) // Gọi hàm chỉnh sửa
        }

        holder.btnDelete.setOnClickListener {
            dbHelper.deleteTask(task)
            tasks.removeAt(position) // Cập nhật danh sách
            notifyItemRemoved(position) // Cập nhật RecyclerView
        }
    }

    override fun getItemCount() = tasks.size

    fun updateEditedTask(oldTask: String, newTask: String) {
        val position = tasks.indexOf(oldTask)
        if (position != -1) {
            tasks[position] = newTask
            notifyItemChanged(position)
        }
    }

    fun updateTasks(newTasks: List<String>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
    fun addTask(newTask: String) {
        tasks.add(newTask) // Thêm task vào danh sách
        notifyItemInserted(tasks.size - 1) // Cập nhật RecyclerView ngay lập tức
    }
}


