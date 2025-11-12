package com.sopt.dive.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.util.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _profiles = MutableStateFlow<List<Profile>>(emptyList())
    val profiles: StateFlow<List<Profile>> = _profiles

    var userInfo by mutableStateOf<UserInfo?>(null)
        private set

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

    fun loadUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

}