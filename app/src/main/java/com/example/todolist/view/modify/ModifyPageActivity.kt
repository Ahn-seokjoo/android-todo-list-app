package com.example.todolist.view.modify

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ActivityModyfyPageBinding
import com.example.todolist.view.main.MainFragment.Companion.CONST_TO_DO
import java.time.LocalDateTime

class ModifyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModyfyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModyfyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.modifyText.setText(intent.getStringExtra("presentValue").toString())
    }

    //    REQUEST_CODE_MODIFY
    fun modifyButtonClick(view: View) {
        val intent = Intent()
        val todo = Todo(binding.modifyText.text.toString(), LocalDateTime.now().toString())
        intent.putExtra(CONST_TO_DO, todo)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}