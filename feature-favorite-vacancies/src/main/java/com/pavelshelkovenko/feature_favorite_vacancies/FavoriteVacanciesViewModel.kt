package com.pavelshelkovenko.feature_favorite_vacancies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import com.pavelshelkovenko.feature_search_vacancies.common.mapToVacancyDelegateItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteVacanciesViewModel(
    private val repository: OffersAndVacanciesRepository
): ViewModel() {

    var screenState = MutableStateFlow(FavoriteVacanciesScreenState.Initial as FavoriteVacanciesScreenState)
        private set


    fun loadData() {
        viewModelScope.launch {
            try {
                screenState.update { FavoriteVacanciesScreenState.Loading }
                val vacancies = repository.getFavoriteVacancies()
                screenState.update {
                    FavoriteVacanciesScreenState.Content(
                        vacancies = mapToVacancyDelegateItems(vacancies)
                    )
                }
            } catch (ex: Exception) {
                screenState.update { FavoriteVacanciesScreenState.Error }
            }
        }
    }

    fun toggleVacancyFavoriteStatus(vacancyId: String) {
        viewModelScope.launch {
            repository.getVacancyById(vacancyId).onSuccess { vacancy ->
                val newVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
                repository.saveVacancyInCache(newVacancy)
                val newFavoriteVacancies = repository.getFavoriteVacancies()
                screenState.update {
                    FavoriteVacanciesScreenState.Content(
                        vacancies = mapToVacancyDelegateItems(newFavoriteVacancies)
                    )
                }
            }.onFailure {
                screenState.update { FavoriteVacanciesScreenState.Error }
            }
        }
    }
}