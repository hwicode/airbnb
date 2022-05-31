package com.example.airbnb.ui.priceRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentPriceRangeBinding


class PriceRangeFragment : Fragment() {

    private lateinit var binding: FragmentPriceRangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPriceRangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etLowestPrice.setText("안녕")
    }
}
