package com.example.airbnb.ui.common

import kotlin.math.absoluteValue

object CalendarUtil {

    fun getDayList(date: org.joda.time.LocalDateTime): ArrayList<String> {
        val yearMonth = date.monthOfYear().localDateTime
        val lastDay = yearMonth.dayOfMonth().withMaximumValue().values[2]
        val firstDay = date.withDayOfMonth(1)
        val dayOfWeek = firstDay.dayOfWeek.absoluteValue
        return if (dayOfWeek != 7) {
            getDayListNotStartWithSunday(dayOfWeek, lastDay)
        } else {
            getDayListStartWithSunday(dayOfWeek, lastDay)
        }
    }

    private fun getDayListStartWithSunday(dayOfWeek: Int, lastDay: Int): ArrayList<String> {
        val dayList = arrayListOf<String>()
        for (i in 8..49) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add("")
            } else {
                dayList.add((i - dayOfWeek).toString())
            }
        }
        return dayList
    }

    private fun getDayListNotStartWithSunday(dayOfWeek: Int, lastDay: Int): ArrayList<String> {
        val dayList = arrayListOf<String>()
        for (i in 1..42) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add("")
            } else {
                dayList.add((i - dayOfWeek).toString())
            }
        }
        return dayList
    }
}

//
//private fun getDayList(date: LocalDate):ArrayList<String> {
//    val dayLit = arrayListOf<String>()
//    val yearMonth = YearMonth.from(date)
//    val lastDay = yearMonth.lengthOfMonth()
//    val firstDay = date.withDayOfMonth(1)
//    val dayOfWeek = firstDay.dayOfWeek.value
//
//    if (dayOfWeek != 7) {
//        for (i in 1..42) {
//            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
//                dayLit.add("")
//            } else {
//                dayLit.add((i - dayOfWeek).toString())
//            }
//        }
//    } else {
//        for (i in 8..49) {
//            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
//                dayLit.add("")
//            } else {
//                dayLit.add((i - dayOfWeek).toString())
//            }
//        }
//    }
//    return dayLit
//}