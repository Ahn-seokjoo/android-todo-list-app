package com.example.todolist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.TodoList
import com.example.todolist.databinding.ItemTextBinding
import java.time.LocalDateTime

private val todoList = mutableListOf<TodoList>()//비어있는 리스트로 일단 초기화

class TodoListAdapter(private val itemClickListener: (view: View, position: Int) -> Unit) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    fun submitList(data: List<TodoList>) {
        todoList.addAll(data)
        notifyDataSetChanged()
        //UI를 다시 그리는 메서드
    }

    class ViewHolder(private val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {
        private val todoListText: TextView = binding.checkboxText
        private val currentTime: TextView = binding.currentTimeText

        fun bind(listText: TodoList) {
            todoListText.text = listText.doList
            currentTime.text = LocalDateTime.now().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTextBinding = ItemTextBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(it, position)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}
