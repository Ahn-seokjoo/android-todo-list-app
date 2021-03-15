package com.example.todolist.repository

import com.example.todolist.data.Todo

interface Repository {
    fun getAll(): List<Todo>

    fun addTodo(todo: Todo)

    fun removeTodo(todo: Todo)

    fun updateTodo(todo: Todo)
}