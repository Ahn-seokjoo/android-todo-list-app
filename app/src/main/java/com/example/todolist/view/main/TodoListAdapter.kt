package com.example.todolist.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTextBinding

private val todoList = mutableListOf<Todo>()//비어있는 리스트로 일단 초기화

class TodoListAdapter(private val itemClickListener: (view: View, position: Int) -> Unit) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val checkedMap = mutableMapOf<Todo, Boolean>()
    private var mListener: OnItemLongClickListener? = null

    interface OnItemLongClickListener {
        fun onItemClick(v: View, position: Int)
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.mListener = listener
    }

    fun submitList(data: List<Todo>) {
//        todoList.clear()
        todoList.addAll(data)
        notifyDataSetChanged()
        //UI를 다시 그리는 메서드
    }

     class ViewHolder(val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {
         fun bind(text: Todo, checkedMap: MutableMap<Todo, Boolean>) {
             binding.checkBox.isChecked = checkedMap[todoList[adapterPosition]] ?: false
             binding.todoListText.text = text.doList
             binding.currentTimeText.text = text.time

             binding.todoListText.setOnLongClickListener {
                 Toast.makeText(it.context, "롱클릭", Toast.LENGTH_SHORT).show()

                 return@setOnLongClickListener true
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
        holder.binding.todoListText.setOnClickListener {
            val currentValue = checkedMap[todoList[position]] ?: false
            checkedMap[todoList[position]] = !currentValue
            itemClickListener.invoke(it, position)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}


