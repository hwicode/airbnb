package com.example.airbnb.ui.login

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.common.AccessToken
import com.example.airbnb.domain.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {

    private val sharedpreferences = application.getSharedPreferences("access_code", AppCompatActivity.MODE_PRIVATE)
    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e("Error", ": ${throwable.message}")
        }

    fun getAccessToken() {
        viewModelScope.launch {
            val token = repository.getAccessToken()
            AccessToken.JWT= token
            sharedpreferences.edit().apply{
                putString("token", token)
                apply()
            }
        }
    }
}