package com.example.airbnb.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentSearchBinding
import com.example.airbnb.domain.model.NearDestination
import com.example.airbnb.domain.model.SearchResultDestination
import com.example.airbnb.ui.common.switchFromClearTextToCustomMode
import com.example.airbnb.ui.common.switchFromCustomModeToClearText

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var navigator: NavController
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
        val adapter = NearTravelDestinationAdapter()
        val searchAdapter = SearchResultAdapter {
            moveToInformationInputPage()
        }
        navigator = Navigation.findNavController(view)
        binding.rvSearchNearTravelDestination.adapter = adapter
        binding.rvSearchResultDestination.adapter = searchAdapter
        adapter.submitNearDestinations(makeDummyNearDestinations())
        addSearchListener(searchAdapter)
    }

    private fun addSearchListener(searchAdapter: SearchResultAdapter) {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(searchKey: Editable?) {
                if (searchKey.isNullOrEmpty()) {
                    displayNearDestination()
                } else {
                    displaySearchResultDestination()
                    val dummyList = makeDummySearchResultByKeyword(searchKey.toString())
                    searchAdapter.submitList(dummyList)
                }
            }
        })
    }

    private fun makeDummySearchResultByKeyword(keyword: String): List<SearchResultDestination> {
        val cityList = mutableListOf<SearchResultDestination>()
        for (i in 0..10) {
            cityList.add(SearchResultDestination(keyword))
        }
        return cityList
    }

    private fun makeDummyNearDestinations(): List<NearDestination> {
        val cityList = mutableListOf<NearDestination>()
        for (i in 0..10) {
            cityList.add(NearDestination("imageUrl", "서울", "차로30분"))
        }
        return cityList
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

    private fun moveToInformationInputPage() {
        navigator.navigate(R.id.action_searchFragment_to_informationActivity)
    }
}