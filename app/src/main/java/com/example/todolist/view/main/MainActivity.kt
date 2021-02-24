package com.example.todolist.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.view.add.AddPageActivity
import com.example.todolist.view.modify.ModifyPageActivity

//삭제 기능 구현(체크 표시 된)
//수정시에 수정 후 맨위로 오기
//DB 이용
val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity(), TodoListAdapter.OnItemLongClickListenerInterface {
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

        val adapter = TodoListAdapter({ _, _ -> //꼭 view,position이 아니라 필요한걸 받아줘도 된다
        }, this)

        binding.mRecyclerView.adapter = adapter

    }
    fun addButtonClick(view: View) {
        val intent = Intent(this, AddPageActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ADD)
    }
//    fun deleteButtonClick(view:View){
//        //체크 된 listview 모두 삭제
//        val adapter = TodoListAdapter{_,_-> }
//        binding.mRecyclerView.adapter = adapter
//        adapter.removeItem(adapter.getPosition())
//    }


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
                REQUEST_CODE_MODIFY -> {

                    val todo = data.getSerializableExtra(CONST_TO_DO) as Todo
                    val todoTime: List<Todo> = listOf(todo)

                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(todoTime)
                }
            }
        }
    }

    override fun onItemLongClick(position: Int) {
        val intent = Intent(this, ModifyPageActivity::class.java)
        val adapter = binding.mRecyclerView.adapter as TodoListAdapter
        intent.putExtra("presentValue", adapter.returnItem(position))
        // 현재 클릭한 position 삭제 (수정해야함) + 수정

        adapter.removeItem(position)
        startActivityForResult(intent, REQUEST_CODE_MODIFY)
    }

}

