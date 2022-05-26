package com.example.airbnb.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemCalendarDayBinding

class DayAdapter : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    private val dayList = mutableListOf<String>()

    class ViewHolder(private val binding: ItemCalendarDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(day:String) {
            binding.day= day
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

    fun submitList(list:List<String>){
        this.dayList.addAll(list)
    }


}