package com.example.airbnb.ui.priceRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
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
    private lateinit var skipBtn: Button
    private lateinit var nextBtn: ImageButton
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
        skipBtn = view.rootView.findViewById(R.id.btn_information_skip)
        nextBtn = view.rootView.findViewById(R.id.iBtn_information_next)
        navigator= Navigation.findNavController(view)
        initBtn()
        initPriceRange()
        initChart()
        listenPinPointChange()
        addSkipOrEraseButton()
    }

    private fun initBtn() {
        nextBtn.isSelected = false
        skipBtn.text = getString(R.string.skip_btn_title)
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
            nextBtn.isSelected = true
            skipBtn.text = getString(R.string.erase_btn_title)
            moveWithAllDataInput()
        }

        binding.rbPriceRangeWithChart.onLeftPinChanged = { _, leftPinValue ->
            val rangeTextMin = leftPinValue?.toFloat()?.toInt()?.let {
                if (it <= 0) formatter.format(0)
                else (formatter.format(leftPinValue.toFloat().toInt().times(OMAN_WON)))
            }
            binding.tvInformationRangeStart.text = "₩$rangeTextMin - "
            binding.etLowestPrice.setText("$rangeTextMin")
            nextBtn.isSelected = true
            skipBtn.text = getString(R.string.erase_btn_title)
            moveWithAllDataInput()
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
                seekBarEntries.add(
                    BarEntry(
                        i + SEEKBAR_VALUE_GAP + SEEKBAR_VACANT_GAP,
                        SEEKBAR_VACANT_VALUE
                    )
                )
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


    private fun moveWithAllDataInput() {
        if (nextBtn.isSelected) {
            nextBtn.setOnClickListener {
                navigator.navigate(R.id.action_priceRangeFragment_to_guestRangeFragment)
            }
        }
    }

    private fun addSkipOrEraseButton() {
        skipBtn.setOnClickListener {
            if (skipBtn.text == getString(R.string.skip_btn_title)) {
                navigator.navigate(R.id.action_priceRangeFragment_to_guestRangeFragment)
            } else {
                initChart()
            }
        }
    }
}
