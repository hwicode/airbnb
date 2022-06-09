package com.example.airbnb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.model.CityInfo
import com.example.airbnb.domain.model.CityInfoWithTime
import com.example.airbnb.data.tmap.TmapTimeRequest
import com.example.airbnb.domain.city.CityRepository
import com.example.airbnb.domain.model.RecommendDestination
import com.example.airbnb.domain.model.SearchResultDestination
import com.example.airbnb.domain.tmap.TmapRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tmapRepository: TmapRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _cityInfoStateFlow =
        MutableStateFlow<MutableList<CityInfoWithTime>>(mutableListOf())
    val cityInfoStateFlow: StateFlow<List<CityInfoWithTime>> = _cityInfoStateFlow

    private val _dummyRecommendations = mutableListOf<RecommendDestination>()
    val dummyRecommendations: List<RecommendDestination> = _dummyRecommendations

    private var _myLatitude = 0.0
    private var _myLongitude = 0.0

    init {
        createDummyRecommendations()
    }

    @OptIn(FlowPreview::class)
    private fun loadCityInfo(cityList: List<CityInfo>) {
        viewModelScope.launch {
            cityList.asFlow().flatMapMerge { cityItem ->
                delay(500)
                tmapRepository.getTotalTime(
                    TmapTimeRequest(
                        startY = _myLatitude,
                        startX = _myLongitude,
                        endY = cityItem.latitude,
                        endX = cityItem.longitude
                    )
                )
            }.buffer().collectIndexed { index, value ->
                _cityInfoStateFlow.setList(
                    CityInfoWithTime(
                        cityList[index],
                        value
                    )
                )
            }
        }
    }

    private fun setMyLocation(latitude: Double, longitude: Double) {
        _myLatitude = latitude
        _myLongitude = longitude


    }

    fun setCityInfo(latitude: Double, longitude: Double) {
        if (_myLatitude != latitude || _myLongitude != longitude) {
            setMyLocation(latitude, longitude)
            viewModelScope.launch {
                val cityList = cityRepository.getCityInfo()
                loadCityInfo(cityList)
            }
        }
    }

    private fun <E> MutableStateFlow<MutableList<E>>.setList(element: E) {
        val tempList: MutableList<E> = mutableListOf()
        tempList.addAll(value)
        tempList.add(element)
        this.value = tempList
    }

    private fun createDummyRecommendations() {
        val dummy1 = RecommendDestination(
            "https://images.unsplash.com/photo-1609319172668-8b4f021f3b7b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1976&q=80",
            "자연생활을 만끽할 수\n있는  "
        )
        val dummy2 = RecommendDestination(
            "https://images.unsplash.com/photo-1594478482579-92d8fa3f4a40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80",
            "독특한 공간"
        )
        val dummy3 = RecommendDestination(
            "https://images.unsplash.com/photo-1596141432970-c7711b203305?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80",
            "도심속 즐거움"
        )
        _dummyRecommendations.add(dummy1)
        _dummyRecommendations.add(dummy2)
        _dummyRecommendations.add(dummy3)
    }

    fun dummySearchResultDestination(keyword: String): MutableList<SearchResultDestination> {
        val cityList = mutableListOf<SearchResultDestination>()
        when {
            keyword == "ㅅ" -> {
                cityList.add(SearchResultDestination("서울"))
                cityList.add(SearchResultDestination("수원"))
                return cityList
            }
            keyword.contains("서") -> {
                cityList.add(SearchResultDestination("서울"))
                return cityList
            }
            keyword.contains("수") -> {
                cityList.add(SearchResultDestination("수원"))
                return cityList
            }

            keyword == "대전" -> {
                cityList.add(SearchResultDestination("대전"))
                return cityList

            }
            keyword == "대구" -> {
                cityList.add(SearchResultDestination("대구"))
                return cityList
            }

            keyword == "ㄷ" || keyword.contains("대") -> {
                cityList.add(SearchResultDestination("대구"))
                cityList.add(SearchResultDestination("대전"))
                return cityList
            }

            keyword == "ㄱ" || keyword.contains("과") -> {
                cityList.add(SearchResultDestination("광주"))
                return cityList
            }
            keyword == "ㅇ" -> {
                cityList.add(SearchResultDestination("의정부"))
                cityList.add(SearchResultDestination("울산"))
                return cityList
            }
            keyword.contains("의") || keyword.contains("으") -> {
                cityList.add(SearchResultDestination("의정부"))
                return cityList
            }
            keyword.contains("울") -> {
                cityList.add(SearchResultDestination("울산"))
                return cityList
            }
            keyword == "ㅂ" || keyword.contains("부") -> {
                cityList.add(SearchResultDestination("부천"))
                cityList.add(SearchResultDestination("부산"))
                return cityList
            }
        }
        return cityList

    }
}

