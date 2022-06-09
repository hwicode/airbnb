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

    private val _pageAccommodations = MutableLiveData<List<SearchResult>>()
    val pageAccommodations: LiveData<List<SearchResult>> = _pageAccommodations

    private val _searchCondition = MutableLiveData<SearchCondition>()
    val searchCondition: LiveData<SearchCondition> = _searchCondition


    fun getSearchResultByTag() {
        viewModelScope.launch {
            _searchResult.value =
                repository.getAccomnodationsByTag(_searchCondition.value?.searchTag ?: "")
        }
    }

    fun getSearchResultByAllCondition(){
        viewModelScope.launch {
            _searchCondition.value?.let {
                _searchResult.value= repository.getAccommodationsByAllCondition(it)
            }

        }
    }

    fun updatePage(pageNum: Int) {
        _pageAccommodations.value =
            _searchResult.value?.subList((pageNum - 1) * 10, (pageNum - 1) * 10 + 9)
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