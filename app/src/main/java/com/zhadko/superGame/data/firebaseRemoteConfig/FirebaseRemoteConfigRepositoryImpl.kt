package com.zhadko.superGame.data.firebaseRemoteConfig

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FirebaseRemoteConfigRepositoryImpl(private val context: Context) :
    FirebaseRemoteConfigRepository {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }

    init {
        setupRemoteConfig()
    }

    override fun isWebViewActive(): Boolean {
        return remoteConfig.getBoolean("isWebViewActive")
    }

    private fun setupRemoteConfig() {
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(ContextCompat.getMainExecutor(context)) { task ->
                if (task.isSuccessful) {
                    task.result
                }
            }
    }
}