package com.example.todolist.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.view.add.AddPageActivity


// 수정시에 맨 위로 올라가기(수정 페이지는 원래 데이터 받아와서 수정)
// 길게 누를시에 삭제, 수정 버튼 구현
val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_ADD = 100
        const val REQUEST_CODE_MODIFY = 200
        const val CONST_TO_DO = "todo"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TodoListAdapter { _, _ -> //꼭 view,position이 아니라 필요한걸 받아줘도 된다
//            binding2.checkboxText.isChecked =
//                !binding2.checkboxText.isChecked
        }

        binding.mRecyclerView.adapter = adapter
        //버튼 클릭시에 다음 페이지로 넘어감
    }

    fun buttonClick(view: View) {
        val intent = Intent(this, AddPageActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ADD)
    }

    //데이터를 여기서 받아서 갱신해야함
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_ADD -> {
                    val todo = data.getSerializableExtra(CONST_TO_DO) as Todo
                    val todoTime: List<Todo> = listOf(todo)

                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(todoTime)
                }
            }
        }
    }

}

