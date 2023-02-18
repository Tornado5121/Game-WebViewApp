package com.zhadko.superGame.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhadko.superGame.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.connectivityLiveData.observe(this) { isInternetAvailable ->
            if (!isInternetAvailable) {
                Toast.makeText(
                    this,
                    this.getString(R.string.internet_connection_warning),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}