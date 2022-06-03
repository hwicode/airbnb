package com.example.airbnb.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.common.Constants
import com.example.airbnb.databinding.FragmentLoginWebViewBinding
import okhttp3.internal.wait
import org.koin.android.ext.android.inject

class LoginWebViewFragment : Fragment() {

    private lateinit var binding: FragmentLoginWebViewBinding
    private lateinit var navigator: NavController
    private val viewModel:LoginViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigator = Navigation.findNavController(view)
        setWebView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setWebView() {
        binding.wvLogin.webViewClient = CustomWebViewClient()
        binding.wvLogin.settings.javaScriptEnabled = true
        binding.wvLogin.loadUrl(Constants.WEBVIEW_LOGIN_URL)
    }

    inner class CustomWebViewClient() : WebViewClient() {

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            Log.d("AppTest", "webView Error!!!!!")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d("AppTest", "onPageFinished/ url : ${url}")
            if (url?.contains("code") == true) {
                val includeCodeUrl = url?.split("code")?.get(1)
                includeCodeUrl?.let {
                    Constants.CODE= (includeCodeUrl.subSequence(1, includeCodeUrl.length)).toString()
                }

                viewModel.getAccessToken()
                if(!(Constants.JWT.isNullOrEmpty())){
                    println(Constants.JWT)
                    navigator.navigate(R.id.action_loginWebViewFragment_to_homeFragment)
                }
            }
        }
    }
}
