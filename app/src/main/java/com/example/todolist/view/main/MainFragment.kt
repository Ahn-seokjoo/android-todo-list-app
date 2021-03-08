package com.example.todolist.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.view.adapter.TodoListAdapter
import com.example.todolist.view.add.AddPageActivity
import com.example.todolist.view.modify.ModifyPageActivity


class MainFragment : Fragment() {
    companion object {
        const val REQUEST_CODE_ADD = 100
        const val REQUEST_CODE_MODIFY = 200
        const val CONST_TO_DO = "todo"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel = MainViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val adapter = TodoListAdapter(itemClickListener = { todo -> //꼭 view,position이 아니라 필요한걸 받아줘도 된다
            //수정 화면
            val intent = Intent(requireContext(), ModifyPageActivity::class.java)
            intent.putExtra("presentValue", todo.doList)
            startActivityForResult(intent, REQUEST_CODE_MODIFY)
        }, onItemLongClickListener = { todo ->
            //삭제
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
            builder.setView(dialogView)
                .setPositiveButton("삭제하기") { _, _ ->
                    viewModel.removeTodo(todo)
                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(viewModel.todoList)
                }
                .setNegativeButton("취소") { _, _ ->

                }.show()
        }
        )
        binding.mRecyclerView.adapter = adapter
        binding.addButton.setOnClickListener {
            addButtonClick()
        }
        //상태 복원
        savedInstanceState?.let {
            it.getParcelableArrayList<Todo>("todo")?.let { todoList ->
                viewModel.updateTodoList(todoList)
                adapter.submitList(viewModel.todoList)
            }
        }

        return binding.root
    }

    private fun addButtonClick() {
        val intent = Intent(requireContext(), AddPageActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ADD)
    }

    //바인딩 해제시에 null 해주지 않으면 메모리 누수
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //상태 저장
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("todo", viewModel.todoList as ArrayList)
        super.onSaveInstanceState(outState)
    }

    //데이터를 여기서 받아서 갱신해야함
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_ADD -> {
                    val todo = data.getParcelableExtra<Todo>(CONST_TO_DO) as Todo
                    viewModel.addTodo(todo)

                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(viewModel.todoList)
                }
                REQUEST_CODE_MODIFY -> {
                    val todo = data.getParcelableExtra<Todo>(CONST_TO_DO) as Todo
                    viewModel.updateTodo(todo)

                    val adapter = binding.mRecyclerView.adapter as TodoListAdapter
                    adapter.submitList(viewModel.todoList)
                }
            }
        }
    }
}