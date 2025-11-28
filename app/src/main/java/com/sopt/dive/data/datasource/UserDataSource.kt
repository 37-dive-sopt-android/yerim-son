package com.sopt.dive.data.datasource

import com.sopt.dive.data.BaseResponse
import com.sopt.dive.my.UserDataResponseDto
import com.sopt.dive.signup.SignUpRequestDto
import com.sopt.dive.signup.SignUpResponseDto

interface UserDataSource {
    suspend fun postSignUp(
        request: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

    suspend fun getUserInfo(
        id: Long
    ): BaseResponse<UserDataResponseDto>
}