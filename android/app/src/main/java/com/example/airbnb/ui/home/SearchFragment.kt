package com.example.airbnb.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.common.Constants
import com.example.airbnb.databinding.FragmentSearchBinding
import com.example.airbnb.ui.HomeViewModel
import com.example.airbnb.ui.common.switchFromClearTextToCustomMode
import com.example.airbnb.ui.common.switchFromCustomModeToClearText
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var navigator: NavController
    private lateinit var nearTravelDestinationAdapter: NearTravelDestinationAdapter

    private val viewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nearTravelDestinationAdapter = NearTravelDestinationAdapter(){searchTag->
            moveToInformationInputPage(searchTag)
        }

        val searchAdapter = SearchAdapter { searchTag->
            moveToInformationInputPage(searchTag)
        }
        navigator = Navigation.findNavController(view)
        binding.rvSearchNearTravelDestination.adapter = nearTravelDestinationAdapter
        binding.rvSearchResultDestination.adapter = searchAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setCityList() }
            }
        }
        addSearchListener(searchAdapter)
    }

    private suspend fun setCityList() {
        viewModel.cityInfoStateFlow.collect {
            nearTravelDestinationAdapter.submitList(it)
        }
    }

    private fun addSearchListener(searchAdapter: SearchAdapter) {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(searchKey: Editable?) {
                if (searchKey.isNullOrEmpty()) {
                    displayNearDestination()
                } else {
                    displaySearchResultDestination()
                    val dummyList = viewModel.dummySearchResultDestination(searchKey.toString())
                    searchAdapter.submitList(dummyList)
                }
            }
        })
    }


    private fun displayNearDestination() {
        binding.clSearchNearTravelDestination.isVisible = true
        binding.rvSearchResultDestination.isVisible = false
        binding.etlSearch.switchFromClearTextToCustomMode(requireContext())
    }

    private fun displaySearchResultDestination() {
        binding.clSearchNearTravelDestination.isVisible = false
        binding.rvSearchResultDestination.isVisible = true
        binding.etlSearch.switchFromCustomModeToClearText(requireContext())
    }

    private fun moveToInformationInputPage(tag:String) {
        navigator.navigate(R.id.action_searchFragment_to_informationActivity, bundleOf(Constants.SEARCH_TAG_KEY to tag))
    }
}