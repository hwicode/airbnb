package com.example.airbnb.ui.information

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

class InformationViewModel:ViewModel() {

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
    private fun makeCalendarData(){
        val monthList = Array(12) { index -> LocalDateTime.now().plusMonths(index) }
        monthList.forEach {
            this._calendarDataMap[it] = CalendarUtil.getDayList(it)
        }
    }

    fun eraseSelectedDate(){
        this._checkInStatedFlow.value = null
        this._checkOutStatedFlow.value= null
        initFlag()
    }

    fun switchSkipFlag(){
        viewModelScope.launch {
            _skipFlag.value?.let {
                _skipFlag.value= !it
            }
        }
    }
    fun switchCheckedFlag(){
        viewModelScope.launch {
            _checkedFlag.value?.let {
                _checkedFlag.value= !it
            }
        }
    }

    fun initFlag(){
        _skipFlag.value= true
        _checkedFlag.value= false
    }

}