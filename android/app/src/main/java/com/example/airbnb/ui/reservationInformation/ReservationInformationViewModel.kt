package com.example.airbnb.ui.reservationInformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.model.CalendarDay
import com.example.airbnb.ui.common.CalendarUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class ReservationInformationViewModel : ViewModel() {

    private val _deleteFlag = MutableLiveData(false)
    val deleteFlag: LiveData<Boolean> = _deleteFlag

    private val _checkedFlag = MutableLiveData(false)
    val checkedFlag: LiveData<Boolean> = _checkedFlag

    private val _calendarDataMap: MutableMap<LocalDateTime, List<CalendarDay>> = mutableMapOf()
    val calendarDatMap: Map<LocalDateTime, List<CalendarDay>> = _calendarDataMap

    private val _checkInStatedFlow = MutableStateFlow<LocalDate?>(null)
    val checkInStatedFlow: StateFlow<LocalDate?> = _checkInStatedFlow

    private val _checkOutStatedFlow = MutableStateFlow<LocalDate?>(null)
    val checkOutStatedFlow: StateFlow<LocalDate?> = _checkOutStatedFlow

    private val _adultCountStateFlow = MutableStateFlow(0)
    val adultCountStateFlow = _adultCountStateFlow

    private val _childCountStateFLow = MutableStateFlow(0)
    val childCountStateFlow = _childCountStateFLow

    private val _toddlerCountStateFlow = MutableStateFlow(0)
    val toddlerCountStateFlow = _toddlerCountStateFlow

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

    fun switchCheckedFlag() {
        viewModelScope.launch {
            _checkedFlag.value?.let {
                _checkedFlag.value = !it
            }
        }
    }

    fun initFlag() {
        _deleteFlag.value = false
        _checkedFlag.value = false
    }

    fun setDeleteFlagFalse() {
        _deleteFlag.value = false
    }

    fun setCheckFlagTrue() {
        _checkedFlag.value = true
    }

    fun setCheckFlagFalse() {
        _checkedFlag.value = false
    }

    fun setDeleteFlagTrue() {
        _deleteFlag.value = true
    }

    fun saveAdultCount(count: Int) {
        viewModelScope.launch {
            _adultCountStateFlow.emit(count)
        }
    }

    fun saveChildCount(count: Int) {
        viewModelScope.launch {
            _childCountStateFLow.emit(count)
        }
    }

    fun saveToddlerCount(count: Int) {
        viewModelScope.launch {
            _toddlerCountStateFlow.emit(count)
        }
    }

    fun increaseAdultCountByChildOrToddler(count: Int) {
        viewModelScope.launch {
            _adultCountStateFlow.emit(count)
            setCheckFlagTrue()
        }
    }

    fun initCount() {
        this.adultCountStateFlow.value = 0
        this.childCountStateFlow.value = 0
        this.toddlerCountStateFlow.value = 0
        initFlag()
    }
}