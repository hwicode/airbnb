package com.example.airbnb.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airbnb.domain.model.SearchResult
import com.example.airbnb.domain.model.SearchResultAccommodation

class SearchResultViewModel:ViewModel() {

    private val _searchResult= MutableLiveData<List<SearchResult>>()
    val searchResult:LiveData<List<SearchResult>> = _searchResult

    fun makeDummySearchResultList() {
        val accommodations = mutableListOf<SearchResult>()
        for(i in 0..10){
            accommodations.add(SearchResultAccommodation("dummy Image", true,"서울 왕십리 게스트하우스"))
        }
        _searchResult.value= accommodations
    }
}