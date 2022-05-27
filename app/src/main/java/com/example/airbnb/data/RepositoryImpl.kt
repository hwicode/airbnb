package com.example.airbnb.data

import com.example.airbnb.domain.Repository
import com.example.airbnb.domain.model.CalendarDay
import com.example.airbnb.ui.common.CalendarUtil
import org.joda.time.LocalDateTime

class RepositoryImpl : Repository {
    override fun getCalendarMap():Map<LocalDateTime, List<CalendarDay>> {
        var calendarMap: MutableMap<LocalDateTime, List<CalendarDay>> = mutableMapOf()
        val monthList = Array(12) { index -> LocalDateTime.now().plusMonths(index)}
        monthList.map {
            calendarMap.put(it, CalendarUtil.getDayList(it))
        }

        return calendarMap
    }
}