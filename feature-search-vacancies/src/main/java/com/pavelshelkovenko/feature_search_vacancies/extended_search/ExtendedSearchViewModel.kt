package com.pavelshelkovenko.feature_search_vacancies.extended_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import com.pavelshelkovenko.feature_search_vacancies.common.mapToVacancyDelegateItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExtendedSearchViewModel(
   private val repository: OffersAndVacanciesRepository
): ViewModel() {

    var screenState = MutableStateFlow(ExtendedSearchScreenState.Initial as ExtendedSearchScreenState)
        private set


    fun loadData() {
        viewModelScope.launch {
            screenState.update { ExtendedSearchScreenState.Loading }
            val vacancies = repository.getVacanciesFromCache()
            screenState.update {
                ExtendedSearchScreenState.Content(
                    vacancies = mapToVacancyDelegateItems(vacancies)
                )
            }
        }
    }

    fun toggleVacancyFavoriteStatus(vacancyId: String) {
        viewModelScope.launch {
            repository.getVacancyById(vacancyId).onSuccess { vacancy ->
                val newVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
                repository.saveVacancyInCache(newVacancy)
            }
            val newFavoriteVacancies = repository.getVacanciesFromCache()
            screenState.update {
                ExtendedSearchScreenState.Content(
                    vacancies = mapToVacancyDelegateItems(newFavoriteVacancies)
                )
            }
        }
    }
}