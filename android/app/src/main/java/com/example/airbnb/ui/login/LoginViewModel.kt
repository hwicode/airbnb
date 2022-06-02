package com.example.airbnb.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) :ViewModel(){

    fun getAccessToken(){
        viewModelScope.launch {
            val token= repository.getAccessToken()
            println(token)
        }
    }
}