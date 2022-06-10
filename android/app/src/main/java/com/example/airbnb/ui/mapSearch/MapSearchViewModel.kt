package com.example.airbnb.ui.mapSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResult
import kotlinx.coroutines.launch

class MapSearchViewModel(private val repository: SearchRepository):ViewModel() {

    private val _searchResult = MutableLiveData<List<SearchResult>>()
    val searchResult: LiveData<List<SearchResult>> = _searchResult

    private val _searchCondition = MutableLiveData<SearchCondition>()
    val searchCondition: LiveData<SearchCondition> = _searchCondition


    fun validateSearchCondition(): Boolean {
        val savedSearchCondition = _searchCondition.value
        savedSearchCondition?.let {
            return (it.searchTag.isNotEmpty()) && (it.maxPrice != 0) && (it.minPrice != 0) &&it.checkInDate!=null && it.checkOutDate!=null && it.adultCount != 0
        }
        return false
    }

    fun getSearchResultByTag(pageNum: Int) {
        viewModelScope.launch {
            val list1= repository.getAccomnodationsByTag(_searchCondition.value?.searchTag ?: "", 1)
            val list2= repository.getAccomnodationsByTag(_searchCondition.value?.searchTag ?: "", 2)
            val list3= repository.getAccomnodationsByTag(_searchCondition.value?.searchTag ?: "", 3)
            val list4= repository.getAccomnodationsByTag(_searchCondition.value?.searchTag ?: "", 4)
            val totalList= mutableListOf<SearchResult>()
            totalList.addAll(list1)
            totalList.addAll(list2)
            totalList.addAll(list3)
            totalList.addAll(list4)
            _searchResult.value = totalList

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
}