package com.zhadko.superGame.data.firebaseRealtimeDatabase

import kotlinx.coroutines.flow.SharedFlow

interface FirebaseRealtimeDatabaseRepository {

    val webViewLink: SharedFlow<String>
}