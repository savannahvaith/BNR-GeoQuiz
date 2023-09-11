package com.example.a1_intro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a1_intro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf<Question>(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true, view)
        }

        binding.falseButton.setOnClickListener { view: View ->
           checkAnswer(false, view)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }
        private fun updateQuestion() {
            val questionTextResId = questionBank[currentIndex].textResId
            binding.questionTextView.setText(questionTextResId)

        }

    private fun checkAnswer(userAnswer: Boolean, view: View){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer) R.string.correct_toast else R.string.incorrect_toast
        Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT).show()
    }
}