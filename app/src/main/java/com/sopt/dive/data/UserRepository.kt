package com.sopt.dive.data

import com.sopt.dive.my.UserDataModel
import com.sopt.dive.my.toModel
import com.sopt.dive.signup.SignUpModel
import com.sopt.dive.signup.SignUpRequestModel
import com.sopt.dive.signup.toDto
import com.sopt.dive.signup.toModel

interface UserRepository {
    suspend fun postSignUp(
        request: SignUpRequestModel
    ): Result<SignUpModel>

    suspend fun getUserInfo(
        id: Long
    ): Result<UserDataModel>
}

class UserRepositoryImpl (
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun postSignUp(request: SignUpRequestModel): Result<SignUpModel> =
        suspendRunCatching {
            userDataSource.postSignUp(request = request.toDto()).data!!.toModel()
        }

    override suspend fun getUserInfo(id: Long): Result<UserDataModel> =
        suspendRunCatching {
            userDataSource.getUserInfo(id = id).data!!.toModel()
        }
}