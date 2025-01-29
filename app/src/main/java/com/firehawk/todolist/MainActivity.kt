package com.firehawk.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firehawk.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        taskAdapter = TaskAdapter(tasks) { position ->
            tasks.removeAt(position)
            taskAdapter.notifyItemRemoved(position)
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        // Add Task Button
        binding.btnAddTask.setOnClickListener {
            val taskText = binding.etTaskInput.text.toString()
            if (taskText.isNotEmpty()) {
                tasks.add(taskText)
                taskAdapter.notifyItemInserted(tasks.size - 1)
                binding.etTaskInput.text.clear()
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
