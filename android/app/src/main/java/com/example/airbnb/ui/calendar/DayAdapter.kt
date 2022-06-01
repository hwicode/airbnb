package com.example.airbnb.ui.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R
import com.example.airbnb.databinding.ItemCalendarDayBinding
import com.example.airbnb.domain.model.CalendarDay
import org.joda.time.LocalDate

class DayAdapter(private val itemClick: (selectedDate: LocalDate) -> Unit) :
    RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    private val dayList = mutableListOf<CalendarDay>()
    private var checkIn: LocalDate? = null
    private var checkOut: LocalDate? = null

    inner class ViewHolder(private val binding: ItemCalendarDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var calendarDateToLocalDate: LocalDate

        fun bind(day: CalendarDay, itemClick: (selectedDate: LocalDate) -> Unit) {
            binding.day = day.day

            if (day.day.isNotEmpty()) {
                calendarDateToLocalDate = LocalDate(day.year, day.month, day.day.toInt())

                if (day.isSelectable) {
                    if (day.isStartDay&&checkIn==null) {
                        binding.tvCalendarDay.setBackgroundResource(R.drawable.layout_circle_stroke)
                    }
                    binding.selectableColor = Color.BLACK
                } else {
                    binding.selectableColor = Color.GRAY
                }

                binding.today = calendarDateToLocalDate
                binding.checkIn = checkIn
                binding.checkOut = checkOut

                binding.tvCalendarDay.setOnClickListener {
                    if (day.isSelectable) {
                        itemClick.invoke(calendarDateToLocalDate)
                    }
                }
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCalendarDayBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dayList[position], itemClick)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    fun submitList(list: List<CalendarDay>) {
        this.dayList.addAll(list)
    }

    fun setCheckInAndCheckOut(checkIn: LocalDate?, checkOut: LocalDate?) {
        this.checkIn = checkIn
        this.checkOut = checkOut
    }
}