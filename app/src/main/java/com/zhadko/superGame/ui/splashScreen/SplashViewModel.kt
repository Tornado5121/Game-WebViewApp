package com.zhadko.superGame.ui.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.superGame.data.firebaseRemoteConfig.FirebaseRemoteConfigRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val firebaseRemoteConfigRepository: FirebaseRemoteConfigRepository
) : ViewModel() {

    private val _isWebViewActive = MutableSharedFlow<Boolean>()
    val isWebViewActive = _isWebViewActive.asSharedFlow()

    init {
        setWebViewStatus()
    }

    private fun setWebViewStatus() {
        viewModelScope.launch {
            _isWebViewActive.emit(firebaseRemoteConfigRepository.isWebViewActive())
        }
    }

}