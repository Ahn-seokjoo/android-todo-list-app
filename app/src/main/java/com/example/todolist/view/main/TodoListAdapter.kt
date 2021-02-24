package com.example.todolist.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTextBinding

private val todoList = mutableListOf<Todo>()//비어있는 리스트로 일단 초기화

class TodoListAdapter(private val itemClickListener: (view: View, position: Int) -> Unit, onItemLongClickListener: OnItemLongClickListenerInterface) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val checkedMap = mutableMapOf<Todo, Boolean>()
    private var onItemLongClickListener: OnItemLongClickListenerInterface? = null

    init {
        this.onItemLongClickListener = onItemLongClickListener
    }

    interface OnItemLongClickListenerInterface {
        fun onItemLongClick(position: Int)
    }

    fun submitList(data: List<Todo>) {
//        todoList.clear()
        todoList.addAll(data)
        notifyDataSetChanged()
        //UI를 다시 그리는 메서드
    }

    class ViewHolder(val binding: ItemTextBinding, onItemLongClickListener: OnItemLongClickListenerInterface) : RecyclerView.ViewHolder(binding.root),
        View.OnLongClickListener {
        private var onItemLongClickListener: OnItemLongClickListenerInterface? = null

        init {
            this.onItemLongClickListener = onItemLongClickListener
            itemView.setOnLongClickListener(this)
        }

        fun bind(text: Todo, checkedMap: MutableMap<Todo, Boolean>) {
            binding.checkBox.isChecked = checkedMap[todoList[adapterPosition]] ?: false
            binding.todoListText.text = text.doList
            binding.currentTimeText.text = text.time
        }

        override fun onLongClick(v: View?): Boolean {
            Log.d(TAG, "viewholder long clicked")
            this.onItemLongClickListener?.onItemLongClick(adapterPosition)
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTextBinding = ItemTextBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, this.onItemLongClickListener!!)
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

    fun returnItem(position: Int): String {
        return todoList[position].doList
    }

    fun removeModifyItem(position: Int) {
        todoList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

}


