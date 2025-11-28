package com.sopt.dive.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.data.RepositoryProvider
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.datasourceimpl.UserDataSourceImpl
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl
import com.sopt.dive.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel(
    private val userRepository: UserRepository = RepositoryProvider.userRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MyUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<MyUiState>> = _uiState.asStateFlow()

    fun loadUserInfo(userId: Long){
        viewModelScope.launch {
            userRepository.getUserInfo(userId)
                .onSuccess { userDataModel ->
                    _uiState.update {
                        UiState.Success(
                            MyUiState(
                                id = userDataModel.id,
                                username = userDataModel.username,
                                name = userDataModel.name,
                                email = userDataModel.email,
                                age = userDataModel.age
                            )
                        )
                    }
                }
                .onFailure {
                    _uiState.update { UiState.Failure }
                }
        }
    }
}