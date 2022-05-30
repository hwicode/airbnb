package com.example.airbnb.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentCalendarBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.joda.time.LocalDate

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
        val monthAdapter = MonthAdapter { selectedDate ->
            selectDate(selectedDate)
        }
        binding.rvCalendar.adapter = monthAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setCheckIn() }
                launch { setCheckOut() }
            }
        }
    }

    private suspend fun setCheckIn() {
        viewModel.checkInStatedFlow.collect {
            it?.let {
                binding.checkIn = it
            }
        }
    }

    private suspend fun setCheckOut() {
        viewModel.checkOutStatedFlow.collect {
            it?.let {
                binding.checkOut = it
            }
        }
    }

    private fun selectDate(selectedDate: LocalDate) {
        viewModel.saveDate(selectedDate)

    }
}