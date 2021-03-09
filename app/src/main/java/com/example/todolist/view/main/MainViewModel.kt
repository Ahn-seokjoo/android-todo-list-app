package com.example.todolist.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Todo
import java.util.concurrent.atomic.AtomicInteger

class MainViewModel : ViewModel() {
    var id = AtomicInteger(0)
    private val _todoList = mutableListOf<Todo>()//비어있는 리스트로 일단 초기화

    val todoList: List<Todo>
        get() = _todoList.sortedBy { it.time }.toMutableList()
    val todoListLiveData: LiveData<List<Todo>>
        get() = MutableLiveData(_todoList.toMutableList())

    // 추가
    fun addTodo(todo: Todo) {
        todo.id = id.getAndIncrement()
        _todoList.add(todo)
    }

    //삭제
    fun removeTodo(todo: Todo) {
        _todoList.remove(todo)
    }

    //수정
    fun updateTodo(todo: Todo) {
        val changeData = _todoList.map {
            //매핑해줌
            if (it.id == todo.id) {
                todo
            } else {
                it
            }
        }
        _todoList.apply {
            clear()
            addAll(changeData)
        }
    }

    fun updateTodoList(todoList: List<Todo>) {
        _todoList.clear()
        _todoList.addAll(todoList)
    }

}