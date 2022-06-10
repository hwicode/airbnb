package com.example.airbnb.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResult
import kotlinx.coroutines.launch

class SearchResultViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _searchResult = MutableLiveData<List<SearchResult>>()
    val searchResult: LiveData<List<SearchResult>> = _searchResult

    private val _searchCondition = MutableLiveData<SearchCondition>()
    val searchCondition: LiveData<SearchCondition> = _searchCondition


    fun getSearchResultByTag(pageNum: Int) {
        viewModelScope.launch {
            _searchResult.value =
                repository.getAccomnodationsByTag(_searchCondition.value?.searchTag ?: "", pageNum)
        }
    }

    fun getSearchResultByAllCondition(pageNum: Int){
        viewModelScope.launch {
            _searchCondition.value?.let {
                _searchResult.value= repository.getAccommodationsByAllCondition(it)
            }

        }
    }

    fun loadSearchCondition(searchCondition: SearchCondition) {
        _searchCondition.value = searchCondition
    }

    fun validateSearchCondition(): Boolean {
        val savedSearchCondition = _searchCondition.value
        savedSearchCondition?.let {
            return (it.searchTag.isNotEmpty()) && (it.maxPrice != 0) && (it.minPrice != 0) &&it.checkInDate!=null && it.checkOutDate!=null && it.adultCount != 0
        }
        return false
    }

}