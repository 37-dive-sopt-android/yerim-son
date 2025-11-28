package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.login.LoginModel
import com.sopt.dive.login.LoginRequestModel
import com.sopt.dive.login.toDto
import com.sopt.dive.login.toModel
import com.sopt.dive.util.suspendRunCatching

class AuthRepositoryImpl (
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postLogin(request: LoginRequestModel): Result<LoginModel> =
        suspendRunCatching {
            authDataSource.postLogin(request = request.toDto()).data!!.toModel()
        }
}