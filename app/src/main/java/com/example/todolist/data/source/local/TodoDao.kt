package com.example.todolist.data.source.local

import androidx.room.*
import com.example.todolist.data.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo ORDER BY time")
    fun getAll(): List<Todo>

    @Insert
    fun addTodo(todo: Todo)

    @Delete
    fun removeTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Update
    fun updateTodoList(todoList: List<Todo>)
}