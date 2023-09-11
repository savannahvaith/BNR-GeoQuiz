package com.example.a1_intro

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)
