package com.example.airbnb.ui.common

import com.example.airbnb.common.Constants
import com.example.airbnb.common.DayOfMonthRange
import com.example.airbnb.domain.model.CalendarDay
import kotlin.math.absoluteValue

object CalendarUtil {

    fun getDayList(date: org.joda.time.LocalDateTime): List<CalendarDay> {
        val yearMonth = date.monthOfYear().localDateTime
        val year= date.year
        val lastDay = yearMonth.dayOfMonth().withMaximumValue().values[2]
        val firstDay = date.withDayOfMonth(1)
        val dayOfWeek = firstDay.dayOfWeek.absoluteValue
        return if (dayOfWeek != Constants.SUNDAY_DAY_OF_WEEK) {
            getDayListNotStartWithSunday(year, date.monthOfYear, dayOfWeek, lastDay)
        } else {
            getDayListStartWithSunday(year, date.monthOfYear, dayOfWeek, lastDay)
        }
    }


    private fun getDayListStartWithSunday(year:Int, month: Int, dayOfWeek: Int, lastDay: Int): List<CalendarDay> {
        val dayList = arrayListOf<CalendarDay>()
        val isInStartMonth = (org.joda.time.LocalDateTime.now().monthOfYear == month)
        val nowDay = org.joda.time.LocalDateTime.now().dayOfMonth
        for (i in DayOfMonthRange.START_WITH_SUNDAY.range) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add(CalendarDay(year = year, month = month))
            } else {
                val day = ((i - dayOfWeek).toString())
                if (isInStartMonth) {
                    dayList.add(CalendarDay(year = year, month = month, day = day, isStartDay = (day.toInt() == nowDay), isSelectable = day.toInt() >= nowDay)
                    )
                } else {
                    dayList.add(CalendarDay(year = year, month = month, day = day))
                }
            }
        }
        return dayList
    }

    private fun getDayListNotStartWithSunday(year:Int,month: Int, dayOfWeek: Int, lastDay: Int): List<CalendarDay> {
        val dayList = arrayListOf<CalendarDay>()
        val isInStartMonth = (org.joda.time.LocalDateTime.now().monthOfYear == month)
        val nowDay = org.joda.time.LocalDateTime.now().dayOfMonth
        for (i in DayOfMonthRange.NOT_START_WITH_SUNDAY.range) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add(CalendarDay(year = year, month = month))
            } else {
                val day = ((i - dayOfWeek).toString())
                if (isInStartMonth) {
                    dayList.add(CalendarDay(year = year, month = month, day = day, isStartDay = (day.toInt() == nowDay), isSelectable = day.toInt() >= nowDay))
                } else {
                    dayList.add(CalendarDay(year = year, month = month, day = day))
                }
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