package com.example.airbnb.ui.accommodationDetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityAccommodationBinding
import com.example.airbnb.ui.reservationInformation.ReservationInformation
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AccommodationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccommodationBinding
    private lateinit var accommodationDetailAdapter: AccommodationDetailAdapter
    private val viewModel: AccommodationDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accommodation)
        setContentView(binding.root)
        accommodationDetailAdapter = AccommodationDetailAdapter()
        binding.vpAccommodation.adapter = accommodationDetailAdapter

        binding.btnAccommodationDetailReservation.setOnClickListener {
            showReservationDialog()
        }
        viewModel.loadAccommodationDetail()

        binding.clAccommodationDetailReservation.isVisible = false
        binding.clAccommodationDetailInputInformation.isVisible = true

        binding.btnAccommodationDetailInputInformation.setOnClickListener {
            moveToReservationInformationActivity()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { loadAccommodationDetail() }
            }
        }


    }

    private fun showReservationDialog() {
        val dialog = AccommodationReservationFragment()
        dialog.show(supportFragmentManager, "reservation")
    }

    private fun moveToReservationInformationActivity() {
        val intent = Intent(this, ReservationInformation::class.java)
        startActivity(intent)
    }

    private suspend fun loadAccommodationDetail() {
        viewModel.accommodationDetailStateFlow.collect {
            it?.let {
                binding.accommodationDetail = it
                accommodationDetailAdapter.submitList(it.accommodationImages)
            }
        }
    }
}