package project2024.example.todosqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import project2024.example.todosqlite.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {
    // Declare binding variable for view binding
    private lateinit var binding: ActivityAddTaskBinding
    //Set the content view to the root of the inflated layout
    private lateinit var db: TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAddTaskBinding.inflate(layoutInflater)// Inflate the layout using view binding
        setContentView(binding.root) // Set the content view to the root of the inflated layout

        db = TaskDatabaseHelper(this) // Initialize the SQLite database helper

        // Set click listener for the save button
        binding.saveButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()// Get title from title edit text
            val content = binding.contentEditText.text.toString()
            val task = Task(0,title,content) // Create a Task object with the obtained title and content
            db.insertTask(task)
            finish()
            // Display a toast message indicating successful note saving
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()

        }
    }
}