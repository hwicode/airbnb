package com.example.airbnb.ui.accommodationDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.AccommodationDetailRepository
import com.example.airbnb.domain.model.AccommodationDetailItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccommodationDetailViewModel(private val accommodationDetailRepository: AccommodationDetailRepository) :
    ViewModel() {

    private val _accommodationDetailStateFlow = MutableStateFlow<AccommodationDetailItem?>(null)
    val accommodationDetailStateFlow = _accommodationDetailStateFlow.asStateFlow()


    fun loadAccommodationDetail(accommodationId: Int = 1) {
        viewModelScope.launch {
            _accommodationDetailStateFlow.emit(accommodationDetailRepository.getAccommodationDetail(accommodationId))
        }
    }
}