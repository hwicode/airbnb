package com.example.airbnb.di

import com.example.airbnb.ui.HomeViewModel
import com.example.airbnb.ui.accommodationDetail.AccommodationDetailViewModel
import com.example.airbnb.ui.login.LoginViewModel
import com.example.airbnb.ui.mapSearch.MapSearchViewModel
import com.example.airbnb.ui.reservation.ReservationViewModel
import com.example.airbnb.ui.searchResult.SearchResultViewModel
import com.example.airbnb.ui.wish.WishViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get() ,get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SearchResultViewModel(get())}
    viewModel{ WishViewModel(get())}
    viewModel{ ReservationViewModel(get())}
    viewModel{ AccommodationDetailViewModel(get())}
    viewModel { MapSearchViewModel(get()) }
}