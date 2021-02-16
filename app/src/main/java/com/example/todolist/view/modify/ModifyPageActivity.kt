package com.example.todolist.view.modify

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityModyfyPageBinding
import com.example.todolist.view.main.MainActivity

class ModifyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModyfyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModyfyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun modifyButtonClick(view: View) {
        //1. 있는 데이터 받아오기
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //2. 수정하기
    }


}