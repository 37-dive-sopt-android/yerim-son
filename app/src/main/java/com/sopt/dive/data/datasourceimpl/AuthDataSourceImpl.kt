package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.service.AuthService
import com.sopt.dive.data.BaseResponse
import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.login.LoginRequestDto
import com.sopt.dive.login.LoginResponseDto

class AuthDataSourceImpl (
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun postLogin(request: LoginRequestDto): BaseResponse<LoginResponseDto> =
        authService.postLogin(request = request)
}