package com.example.todolist.view.main

import com.example.todolist.data.Todo

class MainViewModel {
    private val _todoList = mutableListOf<Todo>()//비어있는 리스트로 일단 초기화

    val todoList: List<Todo>
        get() = _todoList

    // 추가
    fun addTodo(todo: Todo) {
        _todoList.add(todo)
    }

    //삭제
    fun removeTodo(todo: Todo) {
        _todoList.remove(todo)
    }

    //수정
    fun updateTodo(todo: Todo) {
//        _todoList.
    }

}