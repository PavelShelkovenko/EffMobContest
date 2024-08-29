package com.pavelshelkovenko.feature_vacancy_details

import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

sealed interface VacancyDetailsScreenState {

    data object Initial : VacancyDetailsScreenState

    data object Loading : VacancyDetailsScreenState

    data object Error: VacancyDetailsScreenState

    data class Content(
        val vacancy: Vacancy,
        val questions: List<DelegateItem>
    ) : VacancyDetailsScreenState
}