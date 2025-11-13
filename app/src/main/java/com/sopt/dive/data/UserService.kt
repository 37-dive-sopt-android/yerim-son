package com.sopt.dive.data

import com.sopt.dive.my.UserDataResponseDto
import com.sopt.dive.signup.SignUpRequestDto
import com.sopt.dive.signup.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("/api/v1/users")
    suspend fun postSignUp(
        @Body request: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getUserInfo(
        @Path("id") userId: Long
    ): BaseResponse<UserDataResponseDto>
}