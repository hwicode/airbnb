package com.example.airbnb.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.common.AccessToken
import com.example.airbnb.common.Constants
import com.example.airbnb.domain.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) :ViewModel(){

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e("Error", ": ${throwable.message}")
        }

    fun getAccessToken(){
        viewModelScope.launch(coroutineExceptionHandler){
            val token = repository.getAccessToken()
            Log.d("뷰모델", "$token")
            AccessToken.JWT= token
        }
    }
}