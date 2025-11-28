package com.sopt.dive.data.datasource

import com.sopt.dive.data.BaseResponse
import com.sopt.dive.login.LoginRequestDto
import com.sopt.dive.login.LoginResponseDto

interface AuthDataSource {
    suspend fun postLogin(
        request: LoginRequestDto
    ): BaseResponse<LoginResponseDto>
}