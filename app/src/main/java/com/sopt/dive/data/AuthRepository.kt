package com.sopt.dive.data

import com.sopt.dive.login.LoginModel
import com.sopt.dive.login.LoginRequestModel
import com.sopt.dive.login.toDto
import com.sopt.dive.login.toModel
import com.sopt.dive.util.suspendRunCatching

interface AuthRepository {
    suspend fun postLogin(
        request: LoginRequestModel
    ): Result<LoginModel>
}

class AuthRepositoryImpl (
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postLogin(request: LoginRequestModel): Result<LoginModel> =
        suspendRunCatching {
            authDataSource.postLogin(request = request.toDto()).data!!.toModel()
        }
}
