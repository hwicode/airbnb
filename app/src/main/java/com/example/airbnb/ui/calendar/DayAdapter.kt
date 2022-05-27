package com.example.airbnb.ui.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemCalendarDayBinding
import com.example.airbnb.domain.model.CalendarDay

class DayAdapter : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    private val dayList = mutableListOf<CalendarDay>()

    class ViewHolder(private val binding: ItemCalendarDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: CalendarDay) {
            binding.day = day.day
            if (day.isSelectable) {
                if (day.isStartDay) {
                    binding.tvCalendarDay.setBackgroundColor(Color.GREEN)
                }
                binding.tvCalendarDay.setTextColor(Color.BLACK)
            } else {
                binding.tvCalendarDay.setTextColor(Color.GRAY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DayAdapter.ViewHolder(ItemCalendarDayBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dayList[position])
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    fun submitList(list: List<CalendarDay>) {
        this.dayList.addAll(list)
    }

}