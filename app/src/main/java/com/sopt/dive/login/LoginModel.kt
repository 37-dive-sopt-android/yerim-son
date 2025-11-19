package com.sopt.dive.login

data class LoginModel(
    val userId: Long,
    val message: String
)

fun LoginResponseDto.toModel() =
    LoginModel(
        userId = this.userId,
        message = this.message
    )

data class LoginRequestModel(
    val username: String,
    val password: String
)

fun LoginRequestModel.toDto() =
    LoginRequestDto(
        username = this.username,
        password = this.password
    )