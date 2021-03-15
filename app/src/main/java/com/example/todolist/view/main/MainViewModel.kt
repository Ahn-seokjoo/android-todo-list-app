package com.example.todolist.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.Todo
import com.example.todolist.repository.DbTodoRepository

class MainViewModel(application: Application) : AndroidViewModel(application) { //안드로이드 뷰모델 사용시 context 얻을 수 있다.

    //    private val repository:Repository = MemoryTodoRepository() // 레파지토리는 뷰모델만 알아야 한다
    private val repository = DbTodoRepository(application)

    private val _todoListLiveData = MutableLiveData<List<Todo>>()

    val todoListLiveData: LiveData<List<Todo>>
        get() = _todoListLiveData

    var selectedTodo: Todo? = null

    // 추가
    fun addTodo(todo: Todo) {
        repository.addTodo(todo)
        _todoListLiveData.value = repository.getAll()
//        Log.d(TAG, "addTodo: 추가 하기")
    }

    //삭제
    fun removeTodo(todo: Todo) {
        repository.removeTodo(todo)
        _todoListLiveData.value = repository.getAll()
//        Log.d(TAG, "removeTodo: 삭제 하기")
    }

    //수정
    fun updateTodo(todo: Todo) {
        repository.updateTodo(todo)
        _todoListLiveData.value = repository.getAll()
//        Log.d(TAG, "updateTodo: 수정 하기")
    }

//    fun updateTodoList(todoList: List<Todo>) {
//        repository.updateTodoList(todoList)
//    }
}