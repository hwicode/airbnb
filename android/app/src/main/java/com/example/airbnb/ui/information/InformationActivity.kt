package com.example.airbnb.ui.information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {

    private var skipFlag: Boolean = true
    private var checkedFlag: Boolean = false
    private lateinit var navController: NavController
    private lateinit var binding: ActivityInformationBinding
    private val viewModel: InformationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)
        setContentView(binding.root)
        supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()
            ?.let { navController = it }

        viewModel.skipFlag.observe(this) {
            skipFlag = it
            skipBtnUpdate()
        }

        viewModel.checkedFlag.observe(this) {
            checkedFlag = it
            binding.iBtnInformationNext.isSelected = checkedFlag
        }

        binding.btnInformationSkip.setOnClickListener {
            val fragment = navController.currentDestination
            fragment?.let {
                when (it.id) {
                    R.id.calendarFragment -> {
                        if (skipFlag) {
                            viewModel.initFlag()
                            viewModel.initChart()
                            navController.navigate(R.id.action_calendarFragment_to_priceRangeFragment)

                        } else {
                            viewModel.switchSkipFlag()
                            viewModel.eraseSelectedDate()
                            viewModel.initFlag()
                        }
                    }
                    R.id.priceRangeFragment -> {
                        if (skipFlag) {
                            viewModel.initFlag()
                            navController.navigate(R.id.action_priceRangeFragment_to_guestRangeFragment)

                        } else {
                            viewModel.switchSkipFlag()
                            viewModel.erasePriceRange()
                        }
                    }
                    R.id.guestRangeFragment -> {
                        if (skipFlag) {
                            viewModel.initFlag()
                            navController.navigate(R.id.action_guestRangeFragment_to_searchResultActivity)
                        } else {
                            viewModel.switchSkipFlag()
                            viewModel.initCount()
                        }
                    }
                }
            }
        }

        binding.iBtnInformationNext.setOnClickListener {
            val fragment = navController.currentDestination
            fragment?.let {
                when (it.id) {
                    R.id.calendarFragment -> {
                        if (checkedFlag) {
                            viewModel.initFlag()
                            viewModel.initChart()
                            navController.navigate(R.id.action_calendarFragment_to_priceRangeFragment)
                        }
                    }
                    R.id.priceRangeFragment -> {
                        if (checkedFlag) {
                            navController.navigate(R.id.action_priceRangeFragment_to_guestRangeFragment)
                            viewModel.initFlag()
                        }
                    }
                    R.id.guestRangeFragment -> {
                        if (checkedFlag) {
                            navController.navigate(R.id.action_guestRangeFragment_to_searchResultActivity)
                            viewModel.initFlag()
                        }
                    }
                    else -> {
                    }
                }
            }
        }

        binding.iBtnInformationBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun skipBtnUpdate() {
        if (skipFlag) binding.btnInformationSkip.text = getString(R.string.skip_btn_title)
        else binding.btnInformationSkip.text = getString(R.string.erase_btn_title)
    }

}