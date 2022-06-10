package com.example.airbnb.ui.accommodationDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemAccommodationDetailBinding

class AccommodationDetailAdapter : RecyclerView.Adapter<AccommodationDetailAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccommodationDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val binding: ItemAccommodationDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodationImage: String) {
            binding.accommodationImage = accommodationImage
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(items:List<String>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

