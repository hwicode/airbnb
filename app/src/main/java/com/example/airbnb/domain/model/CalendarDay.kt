package com.example.airbnb.domain.model

data class CalendarDay(
    val year: Int,
    val month: Int,
    val day: String = "",
    var isStartDay: Boolean = false,
    var isSelectable: Boolean = true,
)