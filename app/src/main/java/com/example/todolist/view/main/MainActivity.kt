package com.example.todolist.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.view.adapter.TodoListAdapter
import com.example.todolist.view.add.AddPageActivity
import com.example.todolist.view.modify.ModifyPageActivity

val TAG = MainActivity::class.java.simpleName


class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_ADD = 100
        const val REQUEST_CODE_MODIFY = 200
        const val CONST_TO_DO = "todo"
    }

    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TodoListAdapter(itemClickListener = { todo -> //꼭 view,position이 아니라 필요한걸 받아줘도 된다
            //수정 화면
            val intent = Intent(this, ModifyPageActivity::class.java)
            intent.putExtra("presentValue", todo.doList)
            startActivityForResult(intent, REQUEST_CODE_MODIFY)
        }, onItemLongClickListener = { todo ->
            //삭제

            viewModel.removeTodo(todo)
        }
        )
        binding.mRecyclerView.adapter = adapter

    }

    fun addButtonClick(view: View) {
        val intent = Intent(this, AddPageActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ADD)
    }

//    add


    //데이터를 여기서 받아서 갱신해야함
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_ADD -> {
                    val todo = data.getSerializableExtra(CONST_TO_DO) as Todo
                    viewModel.addTodo(todo)

                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(viewModel.todoList)
                }
                REQUEST_CODE_MODIFY -> {
                    val todo = data.getSerializableExtra(CONST_TO_DO) as Todo
                    viewModel.updateTodo(todo)

                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(viewModel.todoList)
                }
            }
        }
    }

}

