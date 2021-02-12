package com.example.todolist.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.data.TodoList
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.view.TodoListAdapter

// 클릭 리스너(on,off) 구현
// 액티비티 바뀌어도 데이터 유지
val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = TodoListAdapter(baseContext)
        binding.mRecyclerView.adapter = adapter
    }
    //버튼 클릭시에 다음 페이지로 넘어감
    fun buttonClick(view: View) {
        val intent = Intent(this, AddPage::class.java)
        startActivityForResult(intent, 100)
    }

    //데이터를 여기서 받아서 갱신해야함
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                100 -> {
                    val todoList = data!!.getStringExtra("todoList").toString()
                    val currentTime = data.getStringExtra("currentTime")
                    val todoTimeList: List<TodoList> =
                        listOf(TodoList(todoList, currentTime.toString()))

                    TodoListAdapter(baseContext).submitList(todoTimeList)
                    binding.mRecyclerView.invalidate() //화면 갱신.. 없어도 되긴하는데..
                }
            }
        }
    }


}