package com.example.airbnb.ui.accommodationDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.AccommodationDetailRepository
import com.example.airbnb.domain.model.AccommodationDetailItem
import com.example.airbnb.domain.model.SearchCondition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccommodationDetailViewModel(private val accommodationDetailRepository: AccommodationDetailRepository) :
    ViewModel() {

    private val _accommodationDetailStateFlow = MutableStateFlow<AccommodationDetailItem?>(null)
    val accommodationDetailStateFlow = _accommodationDetailStateFlow.asStateFlow()

    private val _accommodationCondition = MutableStateFlow<SearchCondition?>(null)
    val accommodationCondition = _accommodationCondition.asStateFlow()


    fun loadAccommodationDetail(accommodationId: Int) {
        viewModelScope.launch {
            _accommodationDetailStateFlow.emit(accommodationDetailRepository.getAccommodationDetail(accommodationId))
        }
    }

    fun setAccommodationCondition(condition: SearchCondition) {
        viewModelScope.launch {
            _accommodationCondition.emit(condition)
        }
    }
}