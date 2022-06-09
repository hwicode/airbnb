package com.example.airbnb.ui.reservationInformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityReservationInformationBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ReservationInformation : AppCompatActivity() {

    private val viewModel: ReservationInformationViewModel by viewModels()
    private lateinit var binding: ActivityReservationInformationBinding
    private lateinit var navController: NavController
    private var checkedFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_information)
        setContentView(binding.root)
        supportFragmentManager.findFragmentById(R.id.fragment_reservation_container)
            ?.findNavController()?.let {
                navController = it
            }

        updateDeleteBtn()
        deleteData()
        moveToNextFragment()
        updateCheckBtn()
    }

    private fun updateDeleteBtn() {
        viewModel.deleteFlag.observe(this) {
            binding.btnReservationInformationDelete.isVisible = it
        }
    }

    private fun updateCheckBtn() {
        viewModel.checkedFlag.observe(this) {
            binding.iBtnReservationInformationNext.isSelected = it
            checkedFlag = it
        }
    }

    private fun deleteData() {
        binding.btnReservationInformationDelete.setOnClickListener {
            val currentFragment = navController.currentDestination
            currentFragment?.let {
                when (it.id) {
                    R.id.reservationCalendarFragment -> viewModel.eraseSelectedDate()
                    else -> viewModel.initCount()
                }
            }
        }
    }

    private fun moveToNextFragment() {
        binding.iBtnReservationInformationNext.setOnClickListener {
            if (checkedFlag) {
                val currentFragment = navController.currentDestination
                currentFragment?.let {
                    when (it.id) {
                        R.id.reservationCalendarFragment -> {
                            navController.navigate(R.id.action_reservationCalendarFragment_to_reservationGuestRangeFragment)
                            viewModel.initFlag()
                        }
                        else -> {
                            navController.navigate(R.id.action_reservationGuestRangeFragment_to_accommodationActivity2)
                        }
                    }
                }
            }
        }
    }

}