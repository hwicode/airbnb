package com.example.airbnb.ui.searchResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySearchResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        setContentView(binding.root)
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()

        navController?.let {
            binding.bottomNavigationView.setupWithNavController(it)
        }
    }
}