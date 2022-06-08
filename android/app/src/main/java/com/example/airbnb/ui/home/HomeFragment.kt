package com.example.airbnb.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentHomeBinding
import com.example.airbnb.ui.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navigator: NavController
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var nearTravelDestinationAdapter: NearTravelDestinationAdapter
    private lateinit var recommendAdapter: RecommendAdapter
    private val viewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nearTravelDestinationAdapter = NearTravelDestinationAdapter()
        recommendAdapter = RecommendAdapter()
        navigator = Navigation.findNavController(view)

        binding.rvNearTravelDestination.adapter = nearTravelDestinationAdapter
        binding.rvRecommendTravelDestination.adapter = recommendAdapter
        recommendAdapter.submitRecommendDestinations(viewModel.dummyRecommendations)

        binding.clHomeSearch.setOnClickListener {
            navigator.navigate(R.id.action_homeFragment_to_searchFragment)
        }

        getLastLocation()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setCityList() }
            }
        }
    }

    private suspend fun setCityList() {
        viewModel.cityInfoStateFlow.collect {
            nearTravelDestinationAdapter.submitList(it)
        }
    }


    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = LocationListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude
            Log.d("HomeFragment", "latitude : $latitude, longitude : $longitude")
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            10000L,
            10.0F,
            locationListener
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (isAllPermissionsGranted()) {
            requestLocationUpdates()
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                viewModel.setCityInfo(location.latitude, location.longitude)
            }
        } else {
            requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach { permission ->
                when {
                    permission.value -> {
                        Snackbar.make(binding.root, "Permission granted", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    shouldShowRequestPermissionRationale(permission.key) -> {
                        //Setting 페이지 이동
                    }
                    else -> {
                        Snackbar.make(binding.root, "Permission denied", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    private fun isAllPermissionsGranted(): Boolean = REQUIRED_PERMISSIONS.all { permission ->
        ContextCompat.checkSelfPermission(this.requireContext(), permission) ==
                PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,  // 도시 블록 단위
            Manifest.permission.ACCESS_FINE_LOCATION,  // 더 정밀한 단위
        )
    }

}