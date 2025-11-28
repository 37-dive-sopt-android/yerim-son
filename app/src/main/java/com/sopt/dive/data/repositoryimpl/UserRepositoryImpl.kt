package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.my.UserDataModel
import com.sopt.dive.my.toModel
import com.sopt.dive.signup.SignUpModel
import com.sopt.dive.signup.SignUpRequestModel
import com.sopt.dive.signup.toDto
import com.sopt.dive.signup.toModel
import com.sopt.dive.util.suspendRunCatching

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