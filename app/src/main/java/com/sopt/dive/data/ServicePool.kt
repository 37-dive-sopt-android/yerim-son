package com.sopt.dive.data

import com.sopt.dive.data.service.AuthService
import com.sopt.dive.data.service.UserService

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }

    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
}