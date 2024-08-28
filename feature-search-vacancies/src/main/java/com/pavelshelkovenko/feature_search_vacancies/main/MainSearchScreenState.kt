package com.pavelshelkovenko.feature_search_vacancies.main

import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

sealed interface MainSearchScreenState {

    data object Initial : MainSearchScreenState

    data object Loading : MainSearchScreenState

    data object Error: MainSearchScreenState

    data class Content(
        val offers: List<DelegateItem>,
        val vacancies: List<DelegateItem>
    ) : MainSearchScreenState
}