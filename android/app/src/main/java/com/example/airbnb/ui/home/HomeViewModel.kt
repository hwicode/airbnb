package com.example.airbnb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.data.city.CityDto
import com.example.airbnb.data.city.CityDtoItem
import com.example.airbnb.data.tmap.TmapTimeRequest
import com.example.airbnb.domain.city.CityRepository
import com.example.airbnb.domain.tmap.TmapRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tmapRepository: TmapRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _cityInfoStateFlow = MutableStateFlow<List<CityDtoItem>>(listOf())
    val cityInfoStateFlow: StateFlow<List<CityDtoItem>> = _cityInfoStateFlow
    private var _myLatitude = 0.0
    private var _myLongitude = 0.0

    @OptIn(FlowPreview::class)
    private fun getCityInfo(cityList: CityDto) {
        viewModelScope.launch {
            cityList.asFlow().flatMapMerge { cityItem ->
                delay(500)
                tmapRepository.getTotalTime(
                    TmapTimeRequest(
                        startY = 37.37599,
                        startX = 127.132685,
                        endY = cityItem.latitude,
                        endX = cityItem.longitude
                    )
                )
            }.buffer().collectIndexed { index, value ->
                cityList[index].totalTime = value
                _cityInfoStateFlow.value = cityList.toList()
            }
            println(_cityInfoStateFlow.value)
        }

    }

    fun setMyLocation(latitude: Double, longitude: Double) {
        _myLatitude = latitude
        _myLongitude = longitude

        viewModelScope.launch {
            val cityList = cityRepository.getCityInfo()
            getCityInfo(cityList)
        }
    }


}