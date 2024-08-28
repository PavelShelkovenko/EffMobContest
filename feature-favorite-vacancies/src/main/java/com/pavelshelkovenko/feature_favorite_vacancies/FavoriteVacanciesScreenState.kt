package com.pavelshelkovenko.feature_favorite_vacancies

import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

sealed interface FavoriteVacanciesScreenState {

    data object Initial : FavoriteVacanciesScreenState

    data object Loading : FavoriteVacanciesScreenState

    data class Content(
        val vacancies: List<DelegateItem>
    ) : FavoriteVacanciesScreenState
}