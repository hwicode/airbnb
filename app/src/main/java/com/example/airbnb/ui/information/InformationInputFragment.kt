package com.example.airbnb.ui.information

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentInformationInputBinding


class InformationInputFragment : Fragment() {

    private lateinit var binding: FragmentInformationInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_information_input, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menu = binding.tbarInformationInput.menu
        binding.tbarInformationInput.setNavigationOnClickListener {

        }
        val icon= menu.getItem(0).icon
        DrawableCompat.setTint(icon,(ContextCompat.getColor(requireContext(), R.color.pink)))
    }

}