package com.sopt.dive.data

import com.sopt.dive.login.LoginRequestDto
import com.sopt.dive.login.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun postLogin(
        @Body request: LoginRequestDto
    ): BaseResponse<LoginResponseDto>
}