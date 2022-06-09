package com.example.airbnb.ui.login

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
            val token= requireActivity().getSharedPreferences("access_code", AppCompatActivity.MODE_PRIVATE)
                .getString("token","")
            println(token)
            if(!token.isNullOrEmpty()){
                //이미 저장된 토큰 정보가 있을때
                navigator.navigate(R.id.action_loginWebViewFragment_to_homeFragment)
            }
            else if (url?.contains("code") == true) {
                AccessToken.CODE= Uri.parse(url).getQueryParameters("code").toString()
                viewModel.getAccessToken()
                requireActivity().getSharedPreferences("access_code", AppCompatActivity.MODE_PRIVATE)
                    .edit().apply {
                        putString("token", AccessToken.JWT)
                        apply()
                    }
               //navigator.navigate(R.id.action_loginWebViewFragment_to_homeFragment)
            }
        }
    }
}


