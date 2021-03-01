package com.example.todolist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTextBinding
import com.example.todolist.view.holder.TodoViewHolder

class TodoListAdapter(
    private val itemClickListener: (todo: Todo) -> Unit,
    private val onItemLongClickListener: (todo: Todo) -> Unit
) : ListAdapter<Todo, TodoViewHolder>(TodoDiffCallback) {

    private val checkedMap = mutableMapOf<Todo, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTextBinding = ItemTextBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holderTodo: TodoViewHolder, position: Int) {
        holderTodo.bind(getItem(holderTodo.adapterPosition), checkedMap)

        holderTodo.binding.root.setOnClickListener {
            val currentValue = checkedMap[getItem(holderTodo.adapterPosition)] ?: false
            checkedMap[getItem(holderTodo.adapterPosition)] = !currentValue
            itemClickListener.invoke(getItem(holderTodo.adapterPosition))
            notifyDataSetChanged()
        }
        holderTodo.binding.root.setOnLongClickListener {
            onItemLongClickListener.invoke(getItem(holderTodo.adapterPosition))
            true
        }

    }

}


