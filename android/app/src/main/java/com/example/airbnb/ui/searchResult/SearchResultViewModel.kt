package com.example.airbnb.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.SearchResult
import com.example.airbnb.domain.model.SearchResultAccommodation
import kotlinx.coroutines.launch

class SearchResultViewModel(private val repository: SearchRepository):ViewModel() {

    private val _searchResult= MutableLiveData<List<SearchResult>>()
    val searchResult:LiveData<List<SearchResult>> = _searchResult


    private val _pageAccommodations= MutableLiveData<List<SearchResult>>()
    val pageAccommodations:LiveData<List<SearchResult>> = _pageAccommodations

    init {
        getSearchResult()
    }

    private fun getSearchResult(){
        viewModelScope.launch {
            _searchResult.value= repository.getAccomnodations("서울")
        }
    }

    fun updatePage(pageNum:Int){
        _pageAccommodations.value=  _searchResult.value?.subList((pageNum-1)*10, (pageNum-1)*10+9)
    }

}