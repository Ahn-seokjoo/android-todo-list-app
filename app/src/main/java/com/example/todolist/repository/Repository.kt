package com.example.todolist.repository

import com.example.todolist.data.Todo

interface Repository {
    suspend fun getAll(): List<Todo>

    suspend fun addTodo(todo: Todo)

    suspend fun removeTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)
}