package com.example.airbnb.ui.calendar

import androidx.lifecycle.ViewModel
import com.example.airbnb.data.RepositoryImpl
import com.example.airbnb.domain.Repository
import com.example.airbnb.domain.model.CalendarDay
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class CalendarViewModel : ViewModel() {

    //클릭한 날짜 정보 viewModel까지 가져오는 테스트
    fun saveDate(dateTime: LocalDate){
        println(dateTime)
    }
}