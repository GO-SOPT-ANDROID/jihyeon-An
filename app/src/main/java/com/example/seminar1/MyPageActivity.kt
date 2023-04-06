package com.example.seminar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminar1.databinding.ActivityMyPageBinding
import com.google.android.material.snackbar.Snackbar

class MyPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getName = intent.getStringExtra("name") ?: ""
        val getTalent = intent.getStringExtra("talent") ?: ""

        binding.nameTv.text = "이름 : $getName"
        binding.talentTv.text = "특기 : $getTalent"

        Snackbar.make(
            binding.root,
            "로그인이 완료되었습니다.",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}