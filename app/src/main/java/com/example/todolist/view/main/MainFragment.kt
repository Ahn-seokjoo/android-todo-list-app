package com.example.todolist.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.view.adapter.TodoListAdapter
import com.example.todolist.view.add.AddPageFragment
import com.example.todolist.view.modify.ModifyPageFragment

class MainFragment : Fragment() {
    //프래그먼트 파괴시 파괴
//    private val viewModel: MainViewModel by viewModels()
    //액티비티 파괴시 파괴
//    private val viewModel: MainViewModel by activityViewModels()

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val adapter = TodoListAdapter(itemClickListener = { todo -> //꼭 view,position이 아니라 필요한걸 받아줘도 된다
            //수정 화면
            viewModel.selectedTodo = todo
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<ModifyPageFragment>(R.id.fragment_container_view)
            }
        }, onItemLongClickListener = { todo ->
            //삭제
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
            builder.setView(dialogView)
                .setPositiveButton("삭제하기") { _, _ ->
                    viewModel.removeTodo(todo)
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
        //데이터 관찰
        viewModel.todoListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.d(TAG, "onCreateView: 옵저빙")
        }
        return binding.root
    }

    private fun addButtonClick() {
//        val intent = Intent(requireContext(), AddPageActivity::class.java)
//        startActivityForResult(intent, REQUEST_CODE_ADD)
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<AddPageFragment>(R.id.fragment_container_view)
        }
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

}