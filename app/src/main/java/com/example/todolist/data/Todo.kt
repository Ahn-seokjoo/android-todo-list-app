package com.example.todolist.data

import java.io.Serializable

data class Todo(
    val doList: String,
    val time: String,
    var id: Int = 0
) : Serializable
