package com.zhadko.superGame.di

import com.zhadko.superGame.data.firebaseRealtimeDatabase.FirebaseRealtimeDatabaseRepository
import com.zhadko.superGame.data.firebaseRealtimeDatabase.FirebaseRealtimeDatabaseRepositoryImpl
import com.zhadko.superGame.data.firebaseRemoteConfig.FirebaseRemoteConfigRepository
import com.zhadko.superGame.data.firebaseRemoteConfig.FirebaseRemoteConfigRepositoryImpl
import com.zhadko.superGame.ui.MainViewModel
import com.zhadko.superGame.ui.splashScreen.SplashViewModel
import com.zhadko.superGame.ui.webViewScreen.WebViewViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<FirebaseRemoteConfigRepository> { FirebaseRemoteConfigRepositoryImpl(androidContext()) }
    single<FirebaseRealtimeDatabaseRepository> { FirebaseRealtimeDatabaseRepositoryImpl() }

}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { WebViewViewModel(get()) }
    viewModel { MainViewModel(androidContext()) }
}