package com.example.airbnb.ui.searchResult

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.airbnb.R
import com.example.airbnb.common.Constants
import com.example.airbnb.databinding.ActivitySearchResultBinding
import com.example.airbnb.domain.model.SearchCondition
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySearchResultBinding
    private val viewModel:SearchResultViewModel by viewModel<SearchResultViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadSearchCondition(saveBundleInfo())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        setContentView(binding.root)
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()
        navController?.let {
            binding.bottomNavigationView.setupWithNavController(it)
        }
    }

    private fun saveBundleInfo(): SearchCondition {
        intent.apply {
            val tag=  getStringExtra(Constants.SEARCH_TAG_KEY)?:""
            val checkInDate= getStringExtra(Constants.CHECK_IN_KEY)?:""
            val checkOutDate= getStringExtra(Constants.CHECK_OUT_KEY)?:""
            val maxPrice = getIntExtra(Constants.PRICE_MIN_KEY,0)*Constants.OMAN_WON
            val minPrice = getIntExtra(Constants.PRICE_MAX_KEY,0)*Constants.OMAN_WON
            val adultCount= getIntExtra(Constants.ADULT_KEY,0)
            val childCount= getIntExtra(Constants.CHILD_KEY,0)
            val toddlerCount=getIntExtra(Constants.TODDLER_KEY,0)
            return SearchCondition(tag,checkInDate, checkOutDate, minPrice, maxPrice, adultCount, childCount, toddlerCount)
        }
    }
}