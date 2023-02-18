package com.zhadko.superGame.ui.splashScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.zhadko.superGame.R
import com.zhadko.superGame.base.BaseFragment
import com.zhadko.superGame.databinding.LayoutFragmentSplashBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<LayoutFragmentSplashBinding>(
    vbFactory = LayoutFragmentSplashBinding::inflate
) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.isWebViewActive.collect { isWebViewActive ->
                if (!isWebViewActive) {
                    navigateToWebView()
                } else {
                    navigateToGame()
                }
            }
        }
    }

    private fun navigateToWebView() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToWebViewFragment(),
            navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, inclusive = true)
                .build()
        )
    }

    private fun navigateToGame() {
//        findNavController().navigate()
    }
}