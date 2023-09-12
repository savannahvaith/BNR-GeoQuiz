package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.geoquiz.R.string


private const val TAG:String ="QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel(){

    private val questionBank = listOf<Question>(
        Question(string.question_australia, true),
        Question(string.question_oceans, true),
        Question(string.question_mideast, false),
        Question(string.question_africa, false),
        Question(string.question_americas, true),
        Question(string.question_asia, true)
    )

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}
