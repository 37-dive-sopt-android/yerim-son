package com.sopt.dive.my

import androidx.compose.runtime.Immutable

@Immutable
data class MyUiState(
    val id: Long = 0L,
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int = 0
)