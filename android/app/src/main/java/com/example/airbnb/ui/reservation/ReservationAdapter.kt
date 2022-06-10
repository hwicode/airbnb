package com.example.airbnb.ui.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemReservationBinding
import com.example.airbnb.domain.model.ReservationItem

class ReservationAdapter : RecyclerView.Adapter<ReservationAdapter.ViewHolder>(){

    private val items= mutableListOf<ReservationItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemReservationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ReservationAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemReservationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(reservationItem: ReservationItem) {
            binding.reservationItem= reservationItem
            binding.checkInAndOut= "${reservationItem.checkin}~${reservationItem.checkout}"
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(items:List<ReservationItem>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}