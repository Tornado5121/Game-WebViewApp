package com.zhadko.superGame.ui.webViewScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.zhadko.superGame.base.BaseFragment
import com.zhadko.superGame.databinding.LayoutFragmentWebViewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebViewFragment : BaseFragment<LayoutFragmentWebViewBinding>(
    vbFactory = LayoutFragmentWebViewBinding::inflate
) {

    private val viewModel by viewModel<WebViewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        loadWebView(savedInstanceState)
    }

    private fun setupView() {
        val settings = binding.myWebView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
    }

    private fun loadWebView(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            binding.myWebView.restoreState(savedInstanceState)
        } else {
            lifecycleScope.launch {
                viewModel.webViewLink.collect { link ->
                    binding.myWebView.loadUrl(link)
                }
            }
        }
    }
}