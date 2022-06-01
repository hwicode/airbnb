package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemRecommendDestinationBinding
import com.example.airbnb.domain.model.RecommendDestination

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    private val recommendDestinations = mutableListOf<RecommendDestination>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemRecommendDestinationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecommendAdapter.ViewHolder, position: Int) {
        holder.bind(recommendDestinations[position])
    }

    override fun getItemCount(): Int {
        return recommendDestinations.size
    }

    class ViewHolder(private val binding: ItemRecommendDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: RecommendDestination) {
            binding.destination = destination
            binding.executePendingBindings()
        }
    }

    fun submitRecommendDestinations(destinations: List<RecommendDestination>) {
        this.recommendDestinations.addAll(destinations)
        notifyDataSetChanged()
    }
}