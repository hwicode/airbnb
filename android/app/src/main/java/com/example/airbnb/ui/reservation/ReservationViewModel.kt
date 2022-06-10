package com.example.airbnb.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.PersonalRepository
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.ReservationItem
import kotlinx.coroutines.launch

class ReservationViewModel (searchRepository: PersonalRepository): ViewModel() {
    private val _reservationList= MutableLiveData<List<ReservationItem>>()
    val reservationList: LiveData<List<ReservationItem>> = _reservationList

    init {
        viewModelScope.launch {
            _reservationList.value= searchRepository.getReservationList()
        }
    }

}