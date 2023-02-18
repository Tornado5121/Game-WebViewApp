package com.zhadko.superGame.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.zhadko.superGame.utils.ConnectivityLiveData

class MainViewModel(
    context: Context
) : ViewModel() {

    val connectivityLiveData = ConnectivityLiveData(context)

}