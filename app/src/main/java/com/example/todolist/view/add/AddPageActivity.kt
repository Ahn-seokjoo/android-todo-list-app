package com.example.todolist.view.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ActivityAddPageBinding
import java.time.LocalDateTime


class AddPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)
        binding = ActivityAddPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun addButtonClick(view: View) {
        val intent = Intent()
        val todo = Todo(binding.todoText.text.toString(), LocalDateTime.now().toString())
        intent.putExtra("todo", todo)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}