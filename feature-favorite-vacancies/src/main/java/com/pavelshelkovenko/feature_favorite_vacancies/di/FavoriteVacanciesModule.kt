package com.pavelshelkovenko.feature_favorite_vacancies.di

import com.pavelshelkovenko.feature_favorite_vacancies.FavoriteVacanciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteVacanciesModule = module {
    viewModel<FavoriteVacanciesViewModel> { FavoriteVacanciesViewModel(repository = get()) }
}