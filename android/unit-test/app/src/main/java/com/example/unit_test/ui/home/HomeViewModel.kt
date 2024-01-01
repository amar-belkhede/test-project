package com.example.unit_test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unit_test.data.UserRepository
import com.example.unit_test.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment, by view model"
    }
    val text: LiveData<String> = _text

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> get() = _userProfile

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun fetchUserProfile(userId: String) {
        viewModelScope.launch {
            try {
                val userProfile = userRepository.getUserProfile(userId)
                _userProfile.value = userProfile
            } catch (e: Exception) {
                _error.value = "Error fetching user profile"
            }
        }
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val isSuccess = userRepository.login(username, password)
                _loginResult.value = isSuccess
            } catch (e: Exception) {
                // Handle error scenarios
                _loginResult.value = false
            }
        }
    }
}