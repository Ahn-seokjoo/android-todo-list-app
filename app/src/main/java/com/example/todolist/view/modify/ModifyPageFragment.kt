package com.example.todolist.view.modify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todolist.databinding.FragmentModifyPageBinding
import com.example.todolist.view.main.MainViewModel
import java.time.LocalDateTime

class ModifyPageFragment : Fragment() {
    private var _binding: FragmentModifyPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    //뷰가 만들어 지고 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedTodo?.let { todo ->
            binding.modifyText.setText(todo.doList)
            binding.modifyButton.setOnClickListener {
                todo.apply {
                    todo.doList = binding.modifyText.text.toString()
                    todo.time = LocalDateTime.now().toString()
                }
                viewModel.updateTodo(todo)
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}