package com.example.airbnb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentHomeBinding
import com.example.airbnb.domain.model.NearDestination
import com.example.airbnb.domain.model.RecommendDestination

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nearTravelDestinationAdapter = NearTravelDestinationAdapter()
        val recommendAdapter = RecommendAdapter()
        navigator = Navigation.findNavController(view)
        binding.rvNearTravelDestination.adapter = nearTravelDestinationAdapter
        binding.rvRecommendTravelDestination.adapter = recommendAdapter
        nearTravelDestinationAdapter.submitNearDestinations(makeDummyNearDestinations())
        recommendAdapter.submitRecommendDestinations(makeDummyRecommendations())
        binding.clHomeSearch.setOnClickListener {
            navigator.navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun makeDummyNearDestinations(): List<NearDestination> {
        val cityList = mutableListOf<NearDestination>()
        for (i in 0..10) {
            cityList.add(NearDestination("imageUrl", "서울", "차로30분"))
        }
        return cityList
    }

    private fun makeDummyRecommendations(): List<RecommendDestination> {
        val recommendCityList = mutableListOf<RecommendDestination>()
        val dummy1 = RecommendDestination("imageurl", "자연생활을 만끽할 수\n있는  ")
        val dummy2 = RecommendDestination("imageurl", "독특한 공간")
        val dummy3 = RecommendDestination("imageurl", "도심속 즐거움")
        recommendCityList.add(dummy1)
        recommendCityList.add(dummy2)
        recommendCityList.add(dummy3)
        return recommendCityList
    }
}