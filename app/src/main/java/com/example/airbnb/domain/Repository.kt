package com.example.airbnb.domain

import com.example.airbnb.domain.model.CalendarDay

interface Repository {
    fun getCalendarMap(): Map<org.joda.time.LocalDateTime, List<CalendarDay>>
}