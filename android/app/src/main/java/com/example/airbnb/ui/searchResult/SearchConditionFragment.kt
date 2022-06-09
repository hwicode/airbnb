package com.example.airbnb.ui.searchResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentSearchConditionBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchConditionFragment : Fragment() {
    private lateinit var navigator: NavController
    private lateinit var binding: FragmentSearchConditionBinding
    private val viewModel:SearchResultViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_condition, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        binding.iBtnInformationBack.setOnClickListener {
            navigator.navigateUp()
        }
        val searchCondition= viewModel.searchCondition.value
        searchCondition?.let {
            binding.etSearchLocation.setText(it.searchTag)
            binding.etSearchCheckinCheckout.setText("${it.checkInDate}-${it.checkOutDate}")
            binding.etSearchPrice.setText("${it.minPrice}- ${it.maxPrice}")
            binding.etSearchGuestCount.setText("성인 ${it.adultCount} 어린이 ${it.childCount} 유아 ${it.toddlerCount}")
        }

    }
}