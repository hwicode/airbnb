package com.example.airbnb.common

object Constants {
    const val SUNDAY_DAY_OF_WEEK = 7
}

enum class DayOfMonthRange(val range: IntRange) {
    NOT_START_WITH_SUNDAY(1..42),
    START_WITH_SUNDAY(8..49)
}
