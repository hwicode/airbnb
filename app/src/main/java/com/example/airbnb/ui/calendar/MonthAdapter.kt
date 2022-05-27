package com.example.airbnb.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemCalendarMonthBinding
import com.example.airbnb.ui.common.CalendarUtil
import org.joda.time.LocalDateTime

class MonthAdapter : RecyclerView.Adapter<MonthAdapter.ViewHolder>() {

    private val _monthList = Array(12) { index ->
        LocalDateTime.now().plusMonths(index)
    }


    class ViewHolder(private val binding: ItemCalendarMonthBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dateTime: LocalDateTime) {
            binding.tvCalendarMonth.text = dateTime.toDateTime().toString("YYYY년 MM월")
            val dayAdapter = DayAdapter()
            binding.rvCalendarMonth.adapter = dayAdapter
            dayAdapter.submitList(CalendarUtil.getDayList(dateTime))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MonthAdapter.ViewHolder(ItemCalendarMonthBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_monthList[position])
    }

    override fun getItemCount(): Int {
        return _monthList.size
    }


}