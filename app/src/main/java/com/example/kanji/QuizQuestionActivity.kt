package com.example.kanji

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), OnClickListener {
    private lateinit var myTVGlobalOne : TextView
    private lateinit var myTVGlobalTwo : TextView
    private lateinit var myTVGlobalThree : TextView
    private lateinit var myTVGlobalFor : TextView
    private lateinit var btn_submit : Button
    private var mCurrentPosition:Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        val tv_option_one : TextView = findViewById(R.id.tv_option_one)
        val tv_option_two : TextView = findViewById(R.id.tv_option_two)
        val tv_option_three : TextView = findViewById(R.id.tv_option_three)
        val tv_option_for : TextView = findViewById(R.id.tv_option_for)
        val btn_sub : Button = findViewById(R.id.btn_submit)
        myTVGlobalOne = tv_option_one
        myTVGlobalTwo = tv_option_two
        myTVGlobalThree = tv_option_three
        myTVGlobalFor = tv_option_for
        btn_submit = btn_sub
        mQuestionList = Constants.getQuestion()
//        Toast.makeText(this, "${questionList.size}", Toast.LENGTH_SHORT).show()
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_for.setOnClickListener(this)

        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion(){
        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }
        var progressBar : ProgressBar  = findViewById(R.id.progressBar)
        progressBar.progress = mCurrentPosition
        val tv_progress : TextView = findViewById(R.id.tv_progress)
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max
        val tv_word : TextView = findViewById(R.id.tv_word)
        tv_word.text = question!!.question

        myTVGlobalOne.text = question.optionOne
        myTVGlobalTwo.text = question.optionTwo
        myTVGlobalThree.text = question.optionThree
        myTVGlobalFor.text = question.optionFor
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, myTVGlobalOne)
        options.add(1, myTVGlobalTwo)
        options.add(2, myTVGlobalThree)
        options.add(3, myTVGlobalFor)
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

//    hàm này giúp tv có thể setonclick đc
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_option_one ->{
                selectedOptionView(myTVGlobalOne,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(myTVGlobalTwo,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(myTVGlobalThree,3)
            }
            R.id.tv_option_for ->{
                selectedOptionView(myTVGlobalFor,4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition ++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
//                        đếm số câu đúng
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1->{
                myTVGlobalOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2->{
                myTVGlobalTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3->{
                myTVGlobalThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4->{
                myTVGlobalFor.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
//  đổi màu chữ khi chọn
        tv.setTextColor(Color.parseColor("#D10FE6"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        //  đổi màu background khi chọn
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_boder_bg)
    }
}