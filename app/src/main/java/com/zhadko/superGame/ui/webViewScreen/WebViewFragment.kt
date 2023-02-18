package com.zhadko.superGame.ui.webViewScreen

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
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
        binding.myWebView.webViewClient = WebViewClient()
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.myWebView.canGoBack()) {
                        binding.myWebView.goBack()
                    } else {
                        requireActivity().finish()
                    }
                }
            })
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