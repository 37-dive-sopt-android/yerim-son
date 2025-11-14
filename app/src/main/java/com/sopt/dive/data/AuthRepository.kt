package com.sopt.dive.data

import com.sopt.dive.login.LoginModel
import com.sopt.dive.login.LoginRequestModel
import com.sopt.dive.login.toDto
import com.sopt.dive.login.toModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.cancellation.CancellationException

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

suspend fun <R> suspendRunCatching(block: suspend () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (t: TimeoutCancellationException) {
        Result.failure(t)
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        currentCoroutineContext().ensureActive()
        Result.failure(e)
    }
}