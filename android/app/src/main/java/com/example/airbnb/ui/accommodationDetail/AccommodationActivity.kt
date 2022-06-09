package com.example.airbnb.ui.accommodationDetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityAccommodationBinding
import com.example.airbnb.ui.reservationInformation.ReservationInformation

class AccommodationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccommodationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accommodation)
        setContentView(binding.root)

        binding.btnAccommodationDetailReservation.setOnClickListener {
            showReservationDialog()
        }

        binding.clAccommodationDetailReservation.isVisible = false
        binding.clAccommodationDetailInputInformation.isVisible = true

        binding.btnAccommodationDetailInputInformation.setOnClickListener {
            moveToReservationInformationActivity()
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
}