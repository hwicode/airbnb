package com.example.airbnb.common

object Constants {
    const val SUNDAY_DAY_OF_WEEK = 7
    const val CALENDAR_IN_RANGE_COLOR = 0xFFF3F5F7.toInt()
    const val WEBVIEW_LOGIN_URL = "http://3.35.174.129:8080/"
    const val MOCK_API_URL="https://07608d85-c351-4fed-93b0-97c465642811.mock.pstmn.io/api/"

    const val PRICE_MAX_VALUE = 20
    const val PRICE_MIN_VALUE = 0
    const val SEEKBAR_VALUE_GAP = 0.8f
    const val SEEKBAR_VACANT_GAP = 0.2f
    const val SEEKBAR_VACANT_VALUE = 0.0f
    const val OMAN_WON = 50000
}

enum class DayOfMonthRange(val range: IntRange) {
    NOT_START_WITH_SUNDAY(1..42),
    START_WITH_SUNDAY(8..49)
}

