package com.sopt.dive.data

import com.sopt.dive.login.LoginRequestDto
import com.sopt.dive.login.LoginResponseDto

interface AuthDataSource {
    suspend fun postLogin(
        request: LoginRequestDto
    ): BaseResponse<LoginResponseDto>
}

class AuthDataSourceImpl (
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun postLogin(request: LoginRequestDto): BaseResponse<LoginResponseDto> =
        authService.postLogin(request = request)
}