package com.example.todolist.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}