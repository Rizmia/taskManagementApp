package project2024.example.todosqlite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import project2024.example.todosqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //change 1
    private lateinit var binding: ActivityMainBinding // Declare binding variable for view binding
    private lateinit var db:TaskDatabaseHelper // Declare db variable for SQLite database helper
    private lateinit var  taskAdapter: TaskAdapter // Declare taskAdapter variable for the RecyclerView adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main) // Inflate the layout using view binding
        setContentView(binding.root) // Set the content view to the root of the inflated layout

        db = TaskDatabaseHelper(this)
        taskAdapter = TaskAdapter(db.getAllNotes(),this)


        binding.taskesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.taskesRecyclerView.adapter = taskAdapter



        binding.addButton.setOnClickListener{
            val intent = Intent (this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    // Refresh the data in the RecyclerView adapter onResume to reflect any changes made
    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(db.getAllNotes())
    }
}