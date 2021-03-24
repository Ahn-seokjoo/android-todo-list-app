package com.example.todolist.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentAddPageBinding
import com.example.todolist.view.main.MainViewModel
import java.time.LocalDateTime

class AddPageFragment : Fragment() {
    private var _binding: FragmentAddPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPageBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            val todo = Todo(binding.todoText.text.toString(), LocalDateTime.now().toString())
            viewModel.addTodo(todo)

            //뒤로가기
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}