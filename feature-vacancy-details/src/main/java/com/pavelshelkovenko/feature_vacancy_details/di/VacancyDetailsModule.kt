package com.pavelshelkovenko.feature_vacancy_details.di

import com.pavelshelkovenko.feature_vacancy_details.VacancyDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vacancyDetailsModule = module {
    viewModel<VacancyDetailsViewModel> { VacancyDetailsViewModel(repository = get()) }
}