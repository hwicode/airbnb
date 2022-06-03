package com.example.airbnb.ui.priceRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.common.Constants.OMAN_WON
import com.example.airbnb.common.Constants.PRICE_MAX_VALUE
import com.example.airbnb.common.Constants.PRICE_MIN_VALUE
import com.example.airbnb.databinding.FragmentPriceRangeBinding
import com.example.airbnb.ui.information.InformationViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat


class PriceRangeFragment : Fragment() {

    lateinit var binding: FragmentPriceRangeBinding
    private val viewModel: InformationViewModel by activityViewModels()
    private val formatter = DecimalFormat("#,###")
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPriceRangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        viewModel.switchCheckedFlag()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setChart() }
                launch { setMinPriceText() }
                launch { setMaxPriceText() }
            }
        }
        binding.rbPriceRangeWithChart.setSelectedEntries(viewModel.lowestPriceStatedFlow.value, viewModel.highestPriceStatedFlow.value)
        println(viewModel.lowestPriceStatedFlow.value)
        println(viewModel.highestPriceStatedFlow.value)
        listenPinPointChange()
    }

    private fun listenPinPointChange() {
        binding.rbPriceRangeWithChart.onRightPinChanged = { _, rightPinValue ->
            rightPinValue?.toFloat()?.toInt()?.let {
                viewModel.saveHighestPrice(it)
            }
            viewModel.setSkipFlagFalse()
        }

        binding.rbPriceRangeWithChart.onLeftPinChanged = { _, leftPinValue ->
            leftPinValue?.toFloat()?.toInt()?.let {
                viewModel.saveLowestPrice(it)
            }
            viewModel.setSkipFlagFalse()
        }
    }

    private suspend fun setChart() {
        viewModel.chartStatedFlow.collect {
            binding.rbPriceRangeWithChart.setEntries(it)
            viewModel.setSkipFlagTrue()
        }
    }

    private suspend fun setMinPriceText() {
        viewModel.lowestPriceStatedFlow.collect {
            val price = if (it <= 0) formatter.format(PRICE_MIN_VALUE)
            else (formatter.format(it.times(OMAN_WON)))
            binding.tvInformationRangeStart.text = "₩$price - "
            binding.etLowestPrice.setText("$price")
        }
    }

    private suspend fun setMaxPriceText() {
        viewModel.highestPriceStatedFlow.collect {
            val price = if (it >= 20) formatter.format(PRICE_MAX_VALUE * OMAN_WON) + "+"
            else (formatter.format(it.times(OMAN_WON)))
            binding.tvInformationRangeEnd.text = "₩$price"
            binding.etHighestPrice.setText("$price")
        }
    }

}
