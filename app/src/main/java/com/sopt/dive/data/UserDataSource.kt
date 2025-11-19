package com.sopt.dive.data

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

class UserDataSourceImpl (
    private val userService: UserService
) : UserDataSource {
    override suspend fun postSignUp(request: SignUpRequestDto): BaseResponse<SignUpResponseDto> =
        userService.postSignUp(request = request)

    override suspend fun getUserInfo(id: Long): BaseResponse<UserDataResponseDto> =
        userService.getUserInfo(userId = id)
}