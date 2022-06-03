package com.example.airbnb.ui.priceRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.airbnb.common.Constants.OMAN_WON
import com.example.airbnb.common.Constants.PRICE_MAX_VALUE
import com.example.airbnb.common.Constants.PRICE_MIN_VALUE
import com.example.airbnb.common.Constants.SEEKBAR_VACANT_GAP
import com.example.airbnb.common.Constants.SEEKBAR_VACANT_VALUE
import com.example.airbnb.common.Constants.SEEKBAR_VALUE_GAP
import com.example.airbnb.databinding.FragmentPriceRangeBinding
import com.stfalcon.pricerangebar.model.BarEntry
import java.text.DecimalFormat


class PriceRangeFragment : Fragment() {

    lateinit var binding: FragmentPriceRangeBinding
    private val formatter = DecimalFormat("#,###")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPriceRangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPriceRange()
        initChart()
        listenPinPointChange()
    }

    private fun initPriceRange() {
        binding.tvInformationRangeStart.text = "₩${PRICE_MIN_VALUE} - "
        binding.tvInformationRangeEnd.text = "₩${formatter.format(PRICE_MAX_VALUE * OMAN_WON)}+"
    }


    private fun listenPinPointChange() {
        binding.rbPriceRangeWithChart.onRightPinChanged = { _, rightPinValue ->
            val rangeTextMax = rightPinValue?.toFloat()?.toInt()?.let {
                if (it >= 20) formatter.format(PRICE_MAX_VALUE * OMAN_WON) + "+"
                else (formatter.format(rightPinValue.toFloat().toInt().times(OMAN_WON)))
            }
            binding.tvInformationRangeEnd.text = "₩$rangeTextMax"
            binding.etHighestPrice.setText("$rangeTextMax")
        }

        binding.rbPriceRangeWithChart.onLeftPinChanged = { _, leftPinValue ->
            val rangeTextMin = leftPinValue?.toFloat()?.toInt()?.let {
                if (it <= 0) formatter.format(0)
                else (formatter.format(leftPinValue.toFloat().toInt().times(OMAN_WON)))
            }
            binding.tvInformationRangeStart.text = "₩$rangeTextMin - "
            binding.etLowestPrice.setText("$rangeTextMin")
        }
    }

    private fun initChart() {
        val priceMap = getPriceTestData()
        val seekBarEntries = ArrayList<BarEntry>()

        // 선 그래프를 막대 그래프로 변경하는 로직
        for (i in PRICE_MIN_VALUE..PRICE_MAX_VALUE) {
            priceMap[i]?.let {
                seekBarEntries.add(BarEntry(i.toFloat(), it.toFloat()))
                seekBarEntries.add(BarEntry(i + SEEKBAR_VALUE_GAP, it.toFloat()))
                seekBarEntries.add(BarEntry(i + SEEKBAR_VALUE_GAP, SEEKBAR_VACANT_VALUE))
                seekBarEntries.add(BarEntry(i + SEEKBAR_VALUE_GAP + SEEKBAR_VACANT_GAP, SEEKBAR_VACANT_VALUE))
            }
        }
        binding.rbPriceRangeWithChart.setEntries(seekBarEntries)
    }

    // 추후 viewModel 을 통해 데이터 가져오기 (key: 가격, value: 숙소)
    // 5만원 단위로 숙소를 나눔
    private fun getPriceTestData(): Map<Int, Int> {
        val priceMap = mutableMapOf<Int, Int>()
        val dataSet = List(100) { (1..100).random() }
        dataSet.forEach {
            val key = if (it / 5 >= 20) 20 else it / 5
            priceMap[key] = priceMap[key]?.plus(1) ?: 1
        }
        return priceMap
    }
}
