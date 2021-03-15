package com.example.todolist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Todo(
    var doList: String,
    var time: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0 // 자동으로 증가
) : Parcelable
