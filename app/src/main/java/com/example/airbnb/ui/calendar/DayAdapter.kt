package com.example.airbnb.ui.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemCalendarDayBinding
import com.example.airbnb.domain.model.CalendarDay
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class DayAdapter(private val itemClick: (selectedDate: LocalDate) -> Unit) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    private val dayList = mutableListOf<CalendarDay>()

    class ViewHolder(private val binding: ItemCalendarDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: CalendarDay, itemClick: (selectedDate:LocalDate) -> Unit) {
            binding.day = day.day
            if (day.isSelectable) {
                if (day.isStartDay) {
                    //시작날짜 UI 처리
                }
                binding.selectableColor= Color.BLACK
            } else {
                binding.selectableColor=Color.GRAY
            }
            binding.tvCalendarDay.setOnClickListener {
                itemClick.invoke(LocalDate(day.year, day.month.toInt(), day.day.toInt()))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DayAdapter.ViewHolder(ItemCalendarDayBinding.inflate(inflater, parent, false))
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

    private fun selectDay(selectedDay:LocalDate){


    }
}