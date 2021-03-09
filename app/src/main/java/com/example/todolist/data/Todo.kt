package com.example.todolist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    var doList: String,
    var time: String,
    var id: Int = 0
) : Parcelable
