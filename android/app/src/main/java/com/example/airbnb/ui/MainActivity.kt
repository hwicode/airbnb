package com.example.airbnb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner= this
        setContentView(binding.root)
        setupNav()
    }

    private fun setupNav() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()

        navController?.let {
            binding.bottomNavigationView.setupWithNavController(it)
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> hideBottomNav()
                R.id.loginWebViewFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.isVisible = true
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.isVisible = false
    }
}