package com.example.todolist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.data.TodoList
import com.example.todolist.databinding.ActivityMainBinding

val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    //버튼 클릭시에 다음 페이지로 넘어감
    fun buttonClick(view: View) {
        val intent = Intent(this, AddPage::class.java)
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var todo = arrayListOf<TodoList>()
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                100 -> {
                    val todoList = data!!.getStringExtra("todoList").toString()
                    val currentTime = data.getStringExtra("currentTime")
                    val realTodoList: List<TodoList> =
                        listOf(TodoList(todoList, currentTime.toString()))
                    Log.d(TAG, "onActivityResult: ${realTodoList}")
                    val adapter = TodoListAdapter(baseContext)
                    binding.mRecyclerView.adapter = adapter
                    adapter.submitList(realTodoList)
                }
            }
        }
    }
}