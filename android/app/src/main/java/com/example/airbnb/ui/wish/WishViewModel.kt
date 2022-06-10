package com.example.airbnb.ui.wish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.PersonalRepository
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.WishItem
import kotlinx.coroutines.launch


class WishViewModel(searchRepository: PersonalRepository):ViewModel() {
    private val _wishList= MutableLiveData<List<WishItem>>()
    val wishList:LiveData<List<WishItem>> = _wishList

    init {
        viewModelScope.launch {
            _wishList.value= searchRepository.getWishList()
        }
    }

}