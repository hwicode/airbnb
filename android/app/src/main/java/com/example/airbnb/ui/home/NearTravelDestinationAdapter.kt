package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.data.city.CityDto
import com.example.airbnb.data.city.CityDtoItem
import com.example.airbnb.databinding.ItemNearTravelDestinationBinding

class NearTravelDestinationAdapter :
    RecyclerView.Adapter<NearTravelDestinationAdapter.ViewHolder>() {

    private var nearDestinations = mutableListOf<CityDtoItem>()

    class ViewHolder(private val binding: ItemNearTravelDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityDtoItem: CityDtoItem) {
            binding.cityDtoItem = cityDtoItem

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
        holder.bind(nearDestinations[position])
    }

    override fun getItemCount(): Int {
        return nearDestinations.size
    }

    fun submitNearDestinations(cityDto: List<CityDtoItem>) {
        nearDestinations.addAll(cityDto)
        notifyDataSetChanged()
    }
}







