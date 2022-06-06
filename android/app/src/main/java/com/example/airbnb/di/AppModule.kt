package com.example.airbnb.di

import com.example.airbnb.ui.login.LoginViewModel
import com.example.airbnb.ui.searchResult.SearchResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SearchResultViewModel()}
}