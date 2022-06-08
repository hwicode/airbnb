package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.data.city.CityInfoWithTime
import com.example.airbnb.databinding.ItemNearTravelDestinationBinding

class NearTravelDestinationAdapter :
    ListAdapter<CityInfoWithTime, NearTravelDestinationAdapter.ViewHolder>(CityDiffCallback) {

    class ViewHolder(private val binding: ItemNearTravelDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cityInfo: CityInfoWithTime) {
            binding.cityInfo = cityInfo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNearTravelDestinationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object CityDiffCallback : DiffUtil.ItemCallback<CityInfoWithTime>() {
        override fun areItemsTheSame(
            oldItem: CityInfoWithTime,
            newItem: CityInfoWithTime
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: CityInfoWithTime,
            newItem: CityInfoWithTime
        ): Boolean {
            return oldItem == newItem
        }
    }
}







