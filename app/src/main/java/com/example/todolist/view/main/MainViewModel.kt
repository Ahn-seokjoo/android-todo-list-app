package com.example.todolist.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.repository.DbTodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) { //안드로이드 뷰모델 사용시 context 얻을 수 있다.

    //    private val repository: Repository = MemoryTodoRepository() // 레파지토리는 뷰모델만 알아야 한다
    private val repository = DbTodoRepository(application)

    private val _todoListLiveData = MutableLiveData<List<Todo>>()

    val todoListLiveData: LiveData<List<Todo>>
        get() = _todoListLiveData

    var selectedTodo: Todo? = null

    // 추가
    fun addTodo(todo: Todo) {
        //Dispatcher.IO => 백 그라운드 스레드
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
            _todoListLiveData.postValue(repository.getAll())
        }
    }

    //삭제
    fun removeTodo(todo: Todo) {
        viewModelScope.launch {
            repository.removeTodo(todo)
            _todoListLiveData.value = repository.getAll()
        }
    }

    //수정
    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateTodo(todo)
                _todoListLiveData.postValue(repository.getAll())
            }
        }
    }

    // 네트워크, 데이터베이스 반드시 백그라운드
    suspend fun updateTodoList(todo: Todo) {
        repository.updateTodoList(todo)
    }
}