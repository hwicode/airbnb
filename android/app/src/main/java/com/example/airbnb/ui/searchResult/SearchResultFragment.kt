package com.example.airbnb.ui.searchResult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentSearchResultBinding
import org.koin.android.ext.android.inject

class SearchResultFragment : Fragment() {

    private val viewModel:SearchResultViewModel by inject()
    private lateinit var binding:FragmentSearchResultBinding
    private lateinit var navigator: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        val adapter= SearchResultAdapter({openDetail()}){
            openCondition()
        }
        binding.rvSearchResult.adapter= adapter
        adapter.submitList(viewModel.makeDummySearchResultList())

    }

    private fun openDetail(){
        //navigate to detail
        navigator.navigate(R.id.action_searchResultFragment_to_accommodationActivity)
    }

    private fun openCondition(){
        //navigate to condition detail
        navigator.navigate(R.id.action_searchResultFragment_to_mapSearchActivity)
    }
}