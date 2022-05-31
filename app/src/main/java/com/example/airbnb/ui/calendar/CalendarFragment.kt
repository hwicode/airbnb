package com.example.airbnb.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentCalendarBinding
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class CalendarFragment : Fragment() {

    private val viewModel: CalendarViewModel by viewModels()
    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val monthAdapter = MonthAdapter{
            selectedDate -> selectDate(selectedDate)
        }
        binding.rvCalendar.adapter = monthAdapter
    }

    private fun selectDate(selectedDate:LocalDate){
        viewModel.saveDate(selectedDate)

    }
}