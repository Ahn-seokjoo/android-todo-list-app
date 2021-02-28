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

    override fun onBindViewHolder(hodlerTodo: TodoViewHolder, position: Int) {
        hodlerTodo.bind(getItem(position), checkedMap)

        hodlerTodo.binding.root.setOnClickListener {
            val currentValue = checkedMap[getItem(position)] ?: false
            checkedMap[getItem(position)] = !currentValue
            itemClickListener.invoke(getItem(position))
            notifyDataSetChanged()
        }
        hodlerTodo.binding.todoListText.setOnClickListener {
            val currentValue = checkedMap[getItem(position)] ?: false
            checkedMap[getItem(position)] = !currentValue
            itemClickListener.invoke(getItem(position))
        }
        hodlerTodo.binding.root.setOnLongClickListener {
            onItemLongClickListener.invoke(getItem(position))
            notifyDataSetChanged()
            true
        }
        hodlerTodo.binding.todoListText.setOnLongClickListener {
            onItemLongClickListener.invoke(getItem(position))
            notifyDataSetChanged()
            true
        }

    }

}


