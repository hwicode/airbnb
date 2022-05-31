package com.example.airbnb.common

object Constants {
    const val SUNDAY_DAY_OF_WEEK = 7
    const val CALENDAR_IN_RANGE_COLOR= 0xFFF3F5F7.toInt()
}

enum class DayOfMonthRange(val range: IntRange) {
    NOT_START_WITH_SUNDAY(1..42),
    START_WITH_SUNDAY(8..49)
}
