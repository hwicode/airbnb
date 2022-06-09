package com.example.airbnb.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentReservationBinding
import org.koin.android.ext.android.inject

class ReservationFragment : Fragment() {
    private lateinit var binding:FragmentReservationBinding
    private val viewModel:ReservationViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_reservation, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= ReservationAdapter()
        binding.rvReservation.adapter= adapter

        viewModel.reservationList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

}