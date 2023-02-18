package com.zhadko.superGame

import android.app.Application
import com.zhadko.superGame.di.dataModule
import com.zhadko.superGame.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}