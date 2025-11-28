package com.sopt.dive.data.repository

import com.sopt.dive.login.LoginModel
import com.sopt.dive.login.LoginRequestModel

interface AuthRepository {
    suspend fun postLogin(
        request: LoginRequestModel
    ): Result<LoginModel>
}