package project2024.example.todosqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import project2024.example.todosqlite.databinding.ActivityUpdataTaskBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdataTaskBinding
    private lateinit var db: TaskDatabaseHelper
    private var taskId: Int = -1 // Variable to hold task ID, initialized to -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdataTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        // Get task ID passed from previous activity
        taskId = intent.getIntExtra("task_id", -1)

        // Check if task ID is valid, if not, finish activity
        if(taskId == -1){
            finish()
            return
        }

        // Retrieve task details from database using task ID
        val task = db.getTaskBYId(taskId)
        binding.updatetitleEditText.setText(task.title)
        binding.updatecontentEditText.setText(task.content)

        binding.updateButton.setOnClickListener{
            val  newTitle = binding.updatetitleEditText.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updatedTask = Task(taskId,newTitle,newContent)
            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Changes Saved",Toast.LENGTH_SHORT).show()
        }


    }
}