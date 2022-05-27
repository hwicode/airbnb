package com.example.airbnb.ui.calendar

import androidx.lifecycle.ViewModel
import com.example.airbnb.data.RepositoryImpl
import com.example.airbnb.domain.Repository
import com.example.airbnb.domain.model.CalendarDay
import org.joda.time.LocalDateTime

class CalendarViewModel :ViewModel(){

    private val repository: Repository= RepositoryImpl()
    fun getCalendarMap(): Map<LocalDateTime, List<CalendarDay>> {
        return repository.getCalendarMap()
    }
}