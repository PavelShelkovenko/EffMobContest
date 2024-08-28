package com.pavelshelkovenko.feature_search_vacancies.extended_search

import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

sealed interface ExtendedSearchScreenState {

    data object Initial : ExtendedSearchScreenState

    data object Loading : ExtendedSearchScreenState

    data class Content(
        val vacancies: List<DelegateItem>
    ) : ExtendedSearchScreenState
}