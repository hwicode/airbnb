package com.example.airbnb.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentCalendarBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.joda.time.LocalDate

class CalendarFragment : Fragment() {

    private val viewModel: CalendarViewModel by viewModels()
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var monthAdapter: MonthAdapter
    private lateinit var skipBtn: Button
    private lateinit var nextBtn: ImageButton
    private lateinit var navigator: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        skipBtn = view.rootView.findViewById<Button>(R.id.btn_information_skip)
        nextBtn = view.rootView.findViewById(R.id.iBtn_information_next)
        println(skipBtn)
        monthAdapter = MonthAdapter { selectedDate ->
            selectDate(selectedDate)
        }
        binding.rvCalendar.adapter = monthAdapter
        monthAdapter.submitCalendarMaps(viewModel.calendarDatMap)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setCheckOut() }
                launch { setCheckIn() }
            }
        }
        addSkipOrEraseButton()
    }

    private suspend fun setCheckIn() {
        viewModel.checkInStatedFlow.collect {
            binding.checkIn = it
            monthAdapter.setCheckInAndCheckOut(it, viewModel.checkOutStatedFlow.value)
            monthAdapter.notifyDataSetChanged()
            checkDateSelection()
        }
    }

    private suspend fun setCheckOut() {
        viewModel.checkOutStatedFlow.collect {
            binding.checkOut = it
            monthAdapter.setCheckInAndCheckOut(viewModel.checkInStatedFlow.value, it)
            monthAdapter.notifyDataSetChanged()
            checkDateSelection()
        }
    }

    private fun selectDate(selectedDate: LocalDate) {
        viewModel.saveDate(selectedDate)
    }

    private fun checkDateSelection() {
        nextBtn.isSelected = binding.checkIn != null && binding.checkOut != null
        if (binding.checkIn != null || binding.checkOut != null) {
            skipBtn.text = getString(R.string.erase_btn_title)
        }
        else{
            skipBtn.text=  getString(R.string.skip_btn_title)
        }
    }

    private fun addSkipOrEraseButton() {
        skipBtn.setOnClickListener {
            if (skipBtn.text ==  getString(R.string.skip_btn_title)) {
                //가격 선택화면 이동
            } else {
                viewModel.eraseSelectedDate()
                monthAdapter.notifyDataSetChanged()
            }
        }
    }
}