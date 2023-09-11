package com.example.a1_intro

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.a1_intro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import logcat.logcat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logcat { "Got a QuizViewModel: $quizViewModel - logcat" }
//        Log.d("MainActivity", "Got a QuizViewModel: $quizViewModel")

        binding.questionTextView.setOnClickListener{
           quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true, view)
        }

        binding.falseButton.setOnClickListener { view: View ->
           checkAnswer(false, view)
        }

        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()

    }
        private fun updateQuestion() {
            val questionTextResId = quizViewModel.currentQuestionText
            binding.questionTextView.setText(questionTextResId)

        }

    private fun checkAnswer(userAnswer: Boolean, view: View){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if(userAnswer == correctAnswer) R.string.correct_toast else R.string.incorrect_toast
        Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT).show()
    }
}
