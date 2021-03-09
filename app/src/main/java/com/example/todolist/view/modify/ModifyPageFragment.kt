package com.example.todolist.view.modify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentModifyPageBinding
import com.example.todolist.view.main.MainFragment
import java.time.LocalDateTime

class ModifyPageFragment : Fragment() {
    private var _binding: FragmentModifyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun modifyButtonClick(view: View) {
        val intent = Intent()
        val todo = Todo(binding.modifyText.text.toString(), LocalDateTime.now().toString())
        intent.putExtra(MainFragment.CONST_TO_DO, todo)
//        setResult(Activity.RESULT_OK, intent)
//        finish()
    }
}