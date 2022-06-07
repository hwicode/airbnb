package com.example.airbnb.ui.accommodationDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityAccommodationBinding

class AccommodationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccommodationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accommodation)
        setContentView(binding.root)

        binding.btnAccommodationDetailReservation.setOnClickListener {
            val testWork: WorkRequest = OneTimeWorkRequest.from(ReservationWorker::class.java)
            val workManger = WorkManager.getInstance(this)
                .enqueue(testWork)
        }
    }
}