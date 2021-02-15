package com.example.todolist.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.data.TodoList
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ItemTextBinding


// 수정시에 맨 위로 올라가기(수정 페이지는 원래 데이터 받아와서 수정)
// 길게 누를시에 삭제, 수정 버튼 구현
val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ItemTextBinding  // 1. 두개를 선언하는게 맞는 것인지
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = ItemTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = TodoListAdapter { _, _ -> //꼭 view,position이 아니라 필요한걸 받아줘도 된다
            binding2.checkboxText.isChecked =
                !binding2.checkboxText.isChecked
        }
        binding.mRecyclerView.adapter = adapter
        //버튼 클릭시에 다음 페이지로 넘어감

    }
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

                    TodoListAdapter().submitList(todoTimeList) //2. 다른 곳에서 쓰이는데 어떻게 ?
                    binding.mRecyclerView.invalidate() //3. 화면 갱신.. 없어도 되긴하는데..
                }
            }
        }
    }

}

