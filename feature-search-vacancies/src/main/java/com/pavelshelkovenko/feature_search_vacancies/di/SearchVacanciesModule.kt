package com.pavelshelkovenko.feature_search_vacancies.di

import com.pavelshelkovenko.feature_search_vacancies.extended_search.ExtendedSearchViewModel
import com.pavelshelkovenko.feature_search_vacancies.main.MainSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchVacanciesModule = module {
    viewModel<MainSearchViewModel> { MainSearchViewModel(repository = get()) }
    viewModel<ExtendedSearchViewModel> { ExtendedSearchViewModel(repository = get()) }
}