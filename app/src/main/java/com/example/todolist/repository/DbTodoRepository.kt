package com.example.todolist.repository

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.Todo
import com.example.todolist.data.source.local.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbTodoRepository(context: Context) : Repository {
    private val db = Room.databaseBuilder(context, TodoDatabase::class.java, "todo.db")
        .build()

    override suspend fun getAll(): List<Todo> = withContext(Dispatchers.IO) {
        return@withContext db.todoDao().getAll()
    }

    override suspend fun addTodo(todo: Todo) {
        db.todoDao().addTodo(todo)
    }

    override suspend fun removeTodo(todo: Todo) = withContext(Dispatchers.IO) {
        db.todoDao().removeTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        db.todoDao().updateTodo(todo)
    }

    // 메인스레드에서 db 접근시 죽는다
    override suspend fun updateTodoList(todo: Todo) {
        db.todoDao().updateTodo(todo)
    }
}
