package com.example.todolist.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTextBinding

class TodoViewHolder(val binding: ItemTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: Todo, checkedMap: MutableMap<Todo, Boolean>) {
        binding.checkBox.isChecked = checkedMap[text] ?: false
        binding.todoListText.text = text.doList
        binding.currentTimeText.text = text.time

    }

}