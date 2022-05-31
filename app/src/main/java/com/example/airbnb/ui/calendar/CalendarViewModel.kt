package com.example.airbnb.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.domain.model.CalendarDay
import com.example.airbnb.ui.common.CalendarUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class CalendarViewModel : ViewModel() {

    private val _calendarDataMap: MutableMap<LocalDateTime, List<CalendarDay>> = mutableMapOf()
    val calendarDatMap: Map<LocalDateTime, List<CalendarDay>> = _calendarDataMap

    private val _checkInStatedFlow = MutableStateFlow<LocalDate?>(null)
    val checkInStatedFlow: StateFlow<LocalDate?> = _checkInStatedFlow

    private val _checkOutStatedFlow = MutableStateFlow<LocalDate?>(null)
    val checkOutStatedFlow: StateFlow<LocalDate?> = _checkOutStatedFlow

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
    }

    init {
        makeCalendarData()
    }
}