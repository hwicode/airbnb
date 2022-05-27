package com.example.airbnb.ui.common

import com.example.airbnb.domain.model.CalendarDay
import kotlin.math.absoluteValue

object CalendarUtil {

    fun getDayList(date: org.joda.time.LocalDateTime): ArrayList<CalendarDay> {
        val yearMonth = date.monthOfYear().localDateTime
        val lastDay = yearMonth.dayOfMonth().withMaximumValue().values[2]
        val firstDay = date.withDayOfMonth(1)
        val dayOfWeek = firstDay.dayOfWeek.absoluteValue
        return if (dayOfWeek != 7) {
            getDayListNotStartWithSunday(date.monthOfYear, dayOfWeek, lastDay)
        } else {
            getDayListStartWithSunday(date.monthOfYear, dayOfWeek, lastDay)
        }
    }

    private fun getDayListStartWithSunday(
        month: Int,
        dayOfWeek: Int,
        lastDay: Int
    ): ArrayList<CalendarDay> {
        val dayList = arrayListOf<CalendarDay>()
        val isInStartMonth = (org.joda.time.LocalDateTime.now().monthOfYear == month)
        val nowDay = org.joda.time.LocalDateTime.now().dayOfMonth
        for (i in 8..49) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add(CalendarDay(month, ""))
            } else {
                val day = ((i - dayOfWeek).toString())
                if (isInStartMonth) {
                    dayList.add(
                        CalendarDay(
                            month,
                            day,
                            day.toInt() == nowDay,
                            day.toInt() >= nowDay
                        )
                    )
                } else {
                    dayList.add(CalendarDay(month, day))
                }
            }
        }
        return dayList
    }

    private fun getDayListNotStartWithSunday(month: Int, dayOfWeek: Int, lastDay: Int
    ): ArrayList<CalendarDay> {
        val dayList = arrayListOf<CalendarDay>()
        val isInStartMonth = (org.joda.time.LocalDateTime.now().monthOfYear == month)
        val nowDay = org.joda.time.LocalDateTime.now().dayOfMonth
        for (i in 1..42) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add(CalendarDay(month, ""))
            } else {
                val day = ((i - dayOfWeek).toString())

                if (isInStartMonth) {
                    dayList.add(
                        CalendarDay(
                            month,
                            day,
                            day.toInt() == nowDay,
                            day.toInt() > nowDay
                        )
                    )
                } else {
                    dayList.add(CalendarDay(month, day))
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