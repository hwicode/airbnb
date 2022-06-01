package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemNearTravelDestinationBinding
import com.example.airbnb.domain.model.NearDestination

class NearTravelDestinationAdapter : RecyclerView.Adapter<NearTravelDestinationAdapter.ViewHolder>() {
    private val nearDestinations = mutableListOf<NearDestination>()

    class ViewHolder(private val binding: ItemNearTravelDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: NearDestination) {
            binding.destination = destination
            binding.executePendingBindings()
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

    fun submitNearDestinations(destinations: List<NearDestination>) {
        this.nearDestinations.addAll(destinations)
        notifyDataSetChanged()
    }
}







