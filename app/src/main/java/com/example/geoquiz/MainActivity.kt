package com.example.geoquiz

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.R.string
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.CheatActivity.Companion
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // handle result
        if(result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            cheatLauncher.launch(intent)

        }


        updateQuestion()

    }
        private fun updateQuestion() {
            val questionTextResId = quizViewModel.currentQuestionText
            binding.questionTextView.setText(questionTextResId)

        }

    private fun checkAnswer(userAnswer: Boolean, view: View){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = when {
            quizViewModel.isCheater -> string.judgement_toast
            userAnswer == correctAnswer -> string.correct_toast
            else -> string.incorrect_toast
        }

        Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT).show()
    }
}
