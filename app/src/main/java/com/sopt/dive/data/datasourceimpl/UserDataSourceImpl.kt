package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.BaseResponse
import com.sopt.dive.data.service.UserService
import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.my.UserDataResponseDto
import com.sopt.dive.signup.SignUpRequestDto
import com.sopt.dive.signup.SignUpResponseDto

class UserDataSourceImpl (
    private val userService: UserService
) : UserDataSource {
    override suspend fun postSignUp(request: SignUpRequestDto): BaseResponse<SignUpResponseDto> =
        userService.postSignUp(request = request)

    override suspend fun getUserInfo(id: Long): BaseResponse<UserDataResponseDto> =
        userService.getUserInfo(userId = id)
}