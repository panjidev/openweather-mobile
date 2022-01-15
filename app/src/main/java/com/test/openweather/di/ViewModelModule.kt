package com.test.openweather.di

import com.test.openweather.presentation.detail.FavoriteViewModel
import com.test.openweather.presentation.home.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        FavoriteViewModel(get())
    }
}