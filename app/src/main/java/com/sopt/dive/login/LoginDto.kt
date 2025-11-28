package com.sopt.dive.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class LoginResponseDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)
