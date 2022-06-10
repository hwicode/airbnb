package com.example.airbnb.ui.accommodationDetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityAccommodationDetailBinding
import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.ui.reservationInformation.ReservationInformation
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccommodationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccommodationDetailBinding
    private lateinit var accommodationDetailAdapter: AccommodationDetailAdapter
    private val viewModel: AccommodationDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accommodation_detail)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        accommodationDetailAdapter = AccommodationDetailAdapter()
        binding.vpAccommodation.adapter = accommodationDetailAdapter

        setViewPagerListener()
        binding.btnAccommodationDetailReservation.setOnClickListener {
            showReservationDialog()
        }

        loadAccommodationDetailInfo()
        loadAccommodationCondition()


        binding.btnAccommodationDetailInputInformation.setOnClickListener {
            moveToReservationInformationActivity()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { loadAccommodationDetail() }
                launch { validateCondition() }
            }
        }
    }

    private fun loadAccommodationDetailInfo() {
        val savedAccommodationID = intent.getIntExtra("id", 1)
        viewModel.loadAccommodationDetail(savedAccommodationID)
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

    private suspend fun validateCondition() {
        viewModel.accommodationCondition.collect {
            it?.let {
                binding.accommodationCondition = it
                showLayout(it.checkOutDate)
            }
        }
    }

    private fun loadAccommodationCondition() {
        val savedAccommodationCondition = intent.getParcelableExtra<SearchCondition>("condition")
        savedAccommodationCondition?.let {
            viewModel.setAccommodationCondition(savedAccommodationCondition)
        }
    }

    private fun setViewPagerListener() {
        // 페이지 리스너 달아보기
        binding.vpAccommodation.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.viewPagerCurrentPage = position + 1
            }
        })
    }

    private fun showLayout(checkOut: String) {
        binding.clAccommodationDetailReservation.isVisible =
            checkOut.isNotEmpty() && checkOut.isNotBlank()
        binding.clAccommodationDetailInputInformation.isVisible =
            !(checkOut.isNotEmpty() && checkOut.isNotBlank())
    }


}