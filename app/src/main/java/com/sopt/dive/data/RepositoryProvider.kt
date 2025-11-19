package com.sopt.dive.data

object RepositoryProvider {
    private val authDataSource: AuthDataSource by lazy {
        AuthDataSourceImpl(ServicePool.authService)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authDataSource)
    }

    private val userDataSource: UserDataSource by lazy {
        UserDataSourceImpl(ServicePool.userService)
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userDataSource)
    }
}