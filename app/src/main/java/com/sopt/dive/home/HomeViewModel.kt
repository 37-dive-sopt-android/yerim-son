package com.sopt.dive.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.RepositoryProvider
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.UserDataSourceImpl
import com.sopt.dive.data.UserRepository
import com.sopt.dive.data.UserRepositoryImpl
import com.sopt.dive.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository = RepositoryProvider.userRepository
): ViewModel() {

    private val _profiles = MutableStateFlow<List<Profile>>(emptyList())
    val profiles: StateFlow<List<Profile>> = _profiles

    private val _uiState = MutableStateFlow<UiState<HomeUiState>>(UiState.Success(HomeUiState()))
    val uiState: StateFlow<UiState<HomeUiState>> = _uiState.asStateFlow()

    init {
        loadDummyProfiles()
    }

    private fun loadDummyProfiles() {
        viewModelScope.launch {
            _profiles.value = List(20) { index ->
                Profile(
                    imageUrl = "https://static.megamart.com/product/image/1392/13924003/13924003_1_960.jpg",
                    name = "안두콩${index + 1}",
                    description = "콩 ${index + 1}개 굴러가유~"
                )
            }
        }
    }

    fun loadUserInfo(userId: Long){
        viewModelScope.launch {
            userRepository.getUserInfo(userId)
                .onSuccess { userDataModel ->
                    _uiState.update {
                        UiState.Success(
                            HomeUiState(
                                name = userDataModel.name
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