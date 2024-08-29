package com.pavelshelkovenko.effmobcontest.di

import com.pavelshelkovenko.effmobcontest.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> { MainViewModel(repository = get()) }
}