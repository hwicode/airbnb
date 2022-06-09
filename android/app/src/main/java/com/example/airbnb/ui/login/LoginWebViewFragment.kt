package com.example.airbnb.ui.login

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.common.AccessToken
import com.example.airbnb.common.Constants
import com.example.airbnb.databinding.FragmentLoginWebViewBinding
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class LoginWebViewFragment : Fragment() {

    private lateinit var binding: FragmentLoginWebViewBinding
    private lateinit var navigator: NavController
    private val viewModel: LoginViewModel by inject()
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

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

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
            val sharedPreferences = requireActivity().getSharedPreferences("access_code", AppCompatActivity.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)
            if (!token.isNullOrEmpty()) {
                AccessToken.JWT= token
                navigator.navigate(R.id.action_loginWebViewFragment_to_homeFragment)
            }
            else if (url?.contains("code") == true) {
                val code = Uri.parse(url).getQueryParameters("code").toString()
                AccessToken.CODE = code.subSequence(1, code.length - 1) as String
                viewModel.getAccessToken()
                navigator.navigate(R.id.action_loginWebViewFragment_to_homeFragment)
            }
        }
    }

}


