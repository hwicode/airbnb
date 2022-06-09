package com.example.airbnb.ui.wish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentWishBinding
import org.koin.android.ext.android.inject

class WishFragment : Fragment() {

    private lateinit var binding: FragmentWishBinding
    private val viewModel: WishViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_wish, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= WishListAdapter()
        binding.rvWish.adapter= adapter
        viewModel.wishList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

}