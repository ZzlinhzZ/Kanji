package com.example.kanji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        var tv_score : TextView = findViewById(R.id.tv_score)
        tv_score.text = "Your score is $correctAnswer out of $totalQuestion"

        val btn_finish : Button = findViewById(R.id.btn_finish)
        btn_finish.setOnClickListener {
            startActivity(Intent(this, MultiQuestionActivity::class.java))
            finish()
        }
    }
}