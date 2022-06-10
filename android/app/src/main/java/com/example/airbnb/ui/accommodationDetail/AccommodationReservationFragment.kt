package com.example.airbnb.ui.accommodationDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.airbnb.data.dto.AccommodationOptionLine
import com.example.airbnb.databinding.FragmentReservationDetailBinding
import kotlinx.coroutines.launch
import org.joda.time.Days
import org.joda.time.format.DateTimeFormat
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.DecimalFormat

class AccommodationReservationFragment : DialogFragment() {

    private lateinit var binding: FragmentReservationDetailBinding
    private val viewModel: AccommodationDetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { loadAccommodationDetail() }
                launch { loadAccommodationCondition() }
            }
        }

    }

    private suspend fun loadAccommodationDetail() {
        viewModel.accommodationDetailStateFlow.collect {
            it?.let {
                binding.accommodationDetail = it
            }
        }
    }

    private suspend fun loadAccommodationCondition() {
        viewModel.accommodationCondition.collect {
            it?.let {
                binding.condition = it
                setTotalPrice(
                    viewModel.accommodationDetailStateFlow.value?.oneDayPerPrice ?: 0,
                    it.checkInDate,
                    it.checkOutDate,
                    viewModel.accommodationDetailStateFlow.value?.accommodationOptionLines
                )
            }
        }
    }

    private fun setTotalPrice(
        price: Int,
        checkIn: String,
        checkOut: String,
        cost: List<AccommodationOptionLine>?
    ) {
        val fullyDayPrice = getFullDayPrice(checkIn, checkOut, price)
        val discount = (fullyDayPrice * 0.04).toInt()
        val serviceFee = (fullyDayPrice * 0.0065).toInt()
        val cleanCost = cost?.get(0)?.price?.toInt() ?: 0
        val tax = (discount * 0.1).toInt()
        val totalPrice = fullyDayPrice - discount + cleanCost + serviceFee + tax
        val formatter = DecimalFormat("#,###")
        binding.tvReservationTotalPriceValue.text = "₩${formatter.format(totalPrice)}"
    }

    private fun getFullDayPrice(
        checkIn: String,
        checkOut: String,
        price: Int
    ): Int {
        val betweenDays = convertStringToDate(checkIn, checkOut)
        return price * betweenDays
    }

    private fun convertStringToDate(
        checkIn: String,
        checkOut: String
    ): Int {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        val checkInDate = formatter.parseLocalDate(checkIn)
        val checkOutDate = formatter.parseLocalDate(checkOut)
        return (Days.daysBetween(checkInDate, checkOutDate).days)
    }

}