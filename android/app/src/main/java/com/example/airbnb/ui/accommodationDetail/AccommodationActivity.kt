package com.example.airbnb.ui.accommodationDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityAccommodationBinding

class AccommodationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccommodationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accommodation)
        setContentView(binding.root)

        binding.btnAccommodationDetailReservation.setOnClickListener {
            showReservationDialog()
        }

    }

    private fun showReservationDialog() {
        val dialog = AccommodationReservationFragment()
        dialog.show(supportFragmentManager, "reservation")
    }
}