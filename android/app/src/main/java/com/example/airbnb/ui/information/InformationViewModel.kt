package com.example.airbnb.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.common.Constants
import com.example.airbnb.common.Constants.PRICE_MAX_VALUE
import com.example.airbnb.common.Constants.PRICE_MIN_VALUE
import com.example.airbnb.domain.model.CalendarDay
import com.example.airbnb.ui.common.CalendarUtil
import com.stfalcon.pricerangebar.model.BarEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class InformationViewModel : ViewModel() {

    private val _skipFlag = MutableLiveData(true)
    val skipFlag: LiveData<Boolean> = _skipFlag

    private val _checkedFlag = MutableLiveData(false)
    val checkedFlag: LiveData<Boolean> = _checkedFlag

    private val _calendarDataMap: MutableMap<LocalDateTime, List<CalendarDay>> = mutableMapOf()
    val calendarDatMap: Map<LocalDateTime, List<CalendarDay>> = _calendarDataMap

    private val _checkInStatedFlow = MutableStateFlow<LocalDate?>(null)
    val checkInStatedFlow: StateFlow<LocalDate?> = _checkInStatedFlow

    private val _checkOutStatedFlow = MutableStateFlow<LocalDate?>(null)
    val checkOutStatedFlow: StateFlow<LocalDate?> = _checkOutStatedFlow

    private var lowestPrice = 0
    private var highestPrice = 20

    private val _lowestPriceStatedFlow = MutableStateFlow<Int>(0)
    val lowestPriceStatedFlow: StateFlow<Int> = _lowestPriceStatedFlow

    private val _highestPriceStatedFlow = MutableStateFlow<Int>(highestPrice)
    val highestPriceStatedFlow: StateFlow<Int> = _highestPriceStatedFlow

    private val _chartStatedFlow = MutableStateFlow<ArrayList<BarEntry>>(arrayListOf())
    val chartStatedFlow: StateFlow<ArrayList<BarEntry>> = _chartStatedFlow

    private var _minIndex = 0
    val minIndex get() = _minIndex
    private var _maxIndex = 20
    private var _lastMaxIndex = 0
    val lastMaxIndex get() = _lastMaxIndex

    init {
        makeCalendarData()
    }

    fun saveDate(dateTime: LocalDate) {
        saveFirstSelectedDay(dateTime)
    }

    private fun saveFirstSelectedDay(dateTime: LocalDate) {
        viewModelScope.launch {
            with(_checkInStatedFlow) {
                this.value?.let {
                    when {
                        it.isEqual(dateTime) -> {
                            this.emit(dateTime)
                            _checkOutStatedFlow.emit(dateTime)
                        }
                        it.isAfter(dateTime) -> {
                            this.emit(dateTime)
                            _checkOutStatedFlow.emit(null)
                        }
                        else -> {
                            _checkOutStatedFlow.emit(dateTime)
                        }
                    }
                } ?: this.emit(dateTime)
            }
        }
    }

    private fun makeCalendarData() {
        val monthList = Array(12) { index -> LocalDateTime.now().plusMonths(index) }
        monthList.forEach {
            this._calendarDataMap[it] = CalendarUtil.getDayList(it)
        }
    }

    fun eraseSelectedDate() {
        this._checkInStatedFlow.value = null
        this._checkOutStatedFlow.value = null
        initFlag()
    }

    fun erasePriceRange() {
        initChart()
    }

    fun switchSkipFlag() {
        viewModelScope.launch {
            _skipFlag.value?.let {
                _skipFlag.value = !it
            }
        }
    }

    fun switchCheckedFlag() {
        viewModelScope.launch {
            _checkedFlag.value?.let {
                _checkedFlag.value = !it
            }
        }
    }

    fun initFlag() {
        _skipFlag.value = true
        _checkedFlag.value = false
    }

    // 여기서부터 PriceRange 관련 메서드

    fun initChart() {
        viewModelScope.launch {
            val priceMap = getPriceTestData()
            val seekBarEntries = ArrayList<BarEntry>()

            // 선 그래프를 막대 그래프로 변경하는 로직
            for (i in Constants.PRICE_MIN_VALUE..Constants.PRICE_MAX_VALUE) {
                priceMap[i]?.let {
                    seekBarEntries.add(BarEntry(i.toFloat(), it.toFloat()))
                    seekBarEntries.add(BarEntry(i + Constants.SEEKBAR_VALUE_GAP, it.toFloat()))
                    seekBarEntries.add(
                        BarEntry(
                            i + Constants.SEEKBAR_VALUE_GAP,
                            Constants.SEEKBAR_VACANT_VALUE
                        )
                    )
                    seekBarEntries.add(
                        BarEntry(
                            i + Constants.SEEKBAR_VALUE_GAP + Constants.SEEKBAR_VACANT_GAP,
                            Constants.SEEKBAR_VACANT_VALUE
                        )
                    )
                }
            }
            _lowestPriceStatedFlow.emit(lowestPrice)
            _highestPriceStatedFlow.emit(highestPrice)
            _chartStatedFlow.emit(seekBarEntries)
        }
    }

    // 추후 viewModel 을 통해 데이터 가져오기 (key: 가격, value: 숙소)
    // 5만원 단위로 숙소를 나눔
    private fun getPriceTestData(): Map<Int, Int> {
        val priceMap = mutableMapOf<Int, Int>()
        val dataSet = List(100) { (1..100).random() }
        dataSet.forEach {
            val key = if (it / 5 >= 20) 20 else it / 5
            priceMap[key] = priceMap[key]?.plus(1) ?: 1
        }
        return priceMap
    }

    fun saveLowestPrice(price: Int) {
        viewModelScope.launch {
            if (price <= PRICE_MIN_VALUE) {
                _lowestPriceStatedFlow.emit(PRICE_MIN_VALUE)
            } else {
                _lowestPriceStatedFlow.emit(price)
            }
        }
    }

    fun saveHighestPrice(price: Int) {
        viewModelScope.launch {
            if (price >= PRICE_MAX_VALUE) {
                _highestPriceStatedFlow.emit(PRICE_MAX_VALUE)
            } else {
                _highestPriceStatedFlow.emit(price)
            }
        }
    }

    fun setSkipFlagFalse() {
        _skipFlag.value = false
    }

    fun setSkipFlagTrue() {
        _skipFlag.value = true
    }

    fun setMinPriceIndex(index: Int) {
        _minIndex = index
    }

    fun setMaxPriceIndex(index: Int) {
        _maxIndex = index
    }

    fun setLastMaxPriceIndex() {
        _lastMaxIndex = _maxIndex
    }

}