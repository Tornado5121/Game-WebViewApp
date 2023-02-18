package com.zhadko.superGame.ui.webViewScreen

import androidx.lifecycle.ViewModel
import com.zhadko.superGame.data.firebaseRealtimeDatabase.FirebaseRealtimeDatabaseRepository

class WebViewViewModel(
    private val firebaseRealtimeDatabaseRepository: FirebaseRealtimeDatabaseRepository
) : ViewModel() {

    val webViewLink = firebaseRealtimeDatabaseRepository.webViewLink

}