package com.sopt.dive.data

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }

    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
}