package com.sopt.dive.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.AuthDataSourceImpl
import com.sopt.dive.data.AuthRepository
import com.sopt.dive.data.AuthRepositoryImpl
import com.sopt.dive.data.ServicePool
import com.sopt.dive.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl(AuthDataSourceImpl(ServicePool.authService))
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<LoginUiState>>(UiState.Success(LoginUiState()))
    val uiState: StateFlow<UiState<LoginUiState>> = _uiState.asStateFlow()

    fun login() {
        val formData = (_uiState.value as? UiState.Success)?.data ?: return
        if (formData.username.isBlank() || formData.password.isBlank()) return

        viewModelScope.launch {
            authRepository.postLogin(
                LoginRequestModel(
                    username = formData.username,
                    password = formData.password
                )
            ).onSuccess { loginModel ->
                _uiState.updateSuccess {
                    it.copy(
                        userId = loginModel.userId
                    )
                }
            }.onFailure {
                _uiState.update { UiState.Failure }
            }
        }
    }

    fun updateUsername(username: String) {
        _uiState.updateSuccess { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.updateSuccess { it.copy(password = password) }
    }

    fun resetLoginState() {
        _uiState.update { UiState.Success(LoginUiState()) }
    }

}

inline fun <T> MutableStateFlow<UiState<T>>.updateSuccess(
    crossinline onUpdate: (T) -> T
) {
    update { currentState ->
        if (currentState is UiState.Success) {
            currentState.copy(data = onUpdate(currentState.data))
        } else {
            currentState
        }
    }
}
