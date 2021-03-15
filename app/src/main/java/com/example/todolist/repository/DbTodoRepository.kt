package com.example.todolist.repository

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.Todo
import com.example.todolist.data.source.local.TodoDatabase

class DbTodoRepository(context: Context) : Repository {
    private val db = Room.databaseBuilder(context, TodoDatabase::class.java, "todo.db")
        .allowMainThreadQueries()
        .build()

    override fun getAll(): List<Todo> {
        return db.todoDao().getAll()
    }

    override fun addTodo(todo: Todo) {
        db.todoDao().addTodo(todo)
    }

    override fun removeTodo(todo: Todo) {
        db.todoDao().removeTodo(todo)
    }

    override fun updateTodo(todo: Todo) {
        db.todoDao().updateTodo(todo)
    }
    // 메인스레드에서 db 접근시 죽는다
}
