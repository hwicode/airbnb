package com.example.airbnb.ui.accommodationDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.airbnb.databinding.FragmentReservationDetailBinding

class AccommodationReservationFragment : DialogFragment() {

    private lateinit var binding: FragmentReservationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }



}