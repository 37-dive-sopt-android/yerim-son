package com.sopt.dive.data.repository

import com.sopt.dive.my.UserDataModel
import com.sopt.dive.signup.SignUpModel
import com.sopt.dive.signup.SignUpRequestModel

interface UserRepository {
    suspend fun postSignUp(
        request: SignUpRequestModel
    ): Result<SignUpModel>

    suspend fun getUserInfo(
        id: Long
    ): Result<UserDataModel>
}