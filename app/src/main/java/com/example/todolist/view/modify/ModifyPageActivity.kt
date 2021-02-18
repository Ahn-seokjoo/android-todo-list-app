package com.example.todolist.view.modify

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityModyfyPageBinding

class ModifyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModyfyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModyfyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
//    REQUEST_CODE_MODIFY
    fun modifyButtonClick(view: View) {
    //1. 있는 데이터 받아오기
    val intent = Intent()
    intent.putExtra("result", binding.modifyText.text.toString())
    setResult(RESULT_OK, intent)
    finish()
    //2. 수정하기
}
}