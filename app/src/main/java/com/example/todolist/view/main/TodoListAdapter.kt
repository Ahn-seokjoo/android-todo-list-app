package com.example.todolist.view.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTextBinding
import com.example.todolist.view.modify.ModifyPageActivity

private val todoList = mutableListOf<Todo>()//비어있는 리스트로 일단 초기화

class TodoListAdapter(private val itemClickListener: (view: View, position: Int) -> Unit) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val checkedMap = mutableMapOf<Todo, Boolean>()

    fun submitList(data: List<Todo>) {
//        todoList.clear()
        todoList.addAll(data)
        notifyDataSetChanged()
        //UI를 다시 그리는 메서드
    }

    inner class ViewHolder(val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: Todo, checkedMap: MutableMap<Todo, Boolean>) {
            binding.checkbox.isChecked = checkedMap[todoList[adapterPosition]] ?: false
            binding.checkboxText.text = text.doList
            binding.currentTimeText.text = text.time
            binding.checkboxText.setOnLongClickListener { v ->
                val intent = Intent(v.context, ModifyPageActivity::class.java)
                v.context.startActivity(intent)
                false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTextBinding = ItemTextBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position], checkedMap)

        holder.itemView.setOnClickListener {
            val currentValue = checkedMap[todoList[position]] ?: false
            checkedMap[todoList[position]] = !currentValue
            itemClickListener.invoke(it, position)
            notifyDataSetChanged()
        }
        holder.binding.checkbox.setOnClickListener {
            val currentValue = checkedMap[todoList[position]] ?: false
            checkedMap[todoList[position]] = !currentValue
            itemClickListener.invoke(it, position)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}


