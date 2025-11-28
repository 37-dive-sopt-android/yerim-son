package com.sopt.dive.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.RepositoryProvider
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.datasourceimpl.UserDataSourceImpl
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl
import com.sopt.dive.login.updateSuccess
import com.sopt.dive.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userRepository: UserRepository = RepositoryProvider.userRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<SignUpUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<SignUpUiState>> = _uiState.asStateFlow()

    fun signUp() {
        val formData = (_uiState.value as? UiState.Success)?.data ?: return
        if (!formData.isValid) return

        viewModelScope.launch {
            userRepository.postSignUp(
                SignUpRequestModel(
                    username = formData.username,
                    password = formData.password,
                    name = formData.name,
                    email = formData.email,
                    age = formData.ageValue!!
                )
            ).onSuccess { signUpModel ->
                _uiState.updateSuccess {
                    it.copy(signUpSuccessName = signUpModel.name)
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

    fun updateName(name: String) {
        _uiState.updateSuccess { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _uiState.updateSuccess { it.copy(email = email) }
    }

    fun updateAge(age: String) {
        val filtered = age.filter { it.isDigit() }
        _uiState.updateSuccess { it.copy(age = filtered) }
    }

    fun resetSignUpState() {
        _uiState.update { UiState.Success(SignUpUiState()) }
    }

}