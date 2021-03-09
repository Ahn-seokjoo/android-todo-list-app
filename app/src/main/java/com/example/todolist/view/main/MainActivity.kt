package com.example.todolist.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R


val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

