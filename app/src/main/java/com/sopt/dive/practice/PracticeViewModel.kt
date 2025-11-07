package com.sopt.dive.practice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PracticeViewModel: ViewModel() {

    var count by mutableIntStateOf(0)
    var isButtonEnabled by mutableStateOf(true)

    fun onButtonClick() {
        count += 1
        checkButtonEnabled()
    }

    private fun checkButtonEnabled() {
        if (count >= 10) {
            isButtonEnabled = false
        }
    }
}