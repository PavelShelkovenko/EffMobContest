package com.pavelshelkovenko.feature_search_vacancies.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import com.pavelshelkovenko.feature_search_vacancies.common.mapToOfferDelegateItems
import com.pavelshelkovenko.feature_search_vacancies.common.mapToVacancyDelegateItems
import com.pavelshelkovenko.feature_search_vacancies.main.MainSearchFragment.Companion.VACANCY_COUNT_TO_SHOW
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainSearchViewModel(
    private val repository: OffersAndVacanciesRepository
) : ViewModel() {

    var screenState = MutableStateFlow(MainSearchScreenState.Initial as MainSearchScreenState)
        private set

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            screenState.update { MainSearchScreenState.Loading }
            repository.getOffersAndVacancies()
                .onSuccess { result ->
                    screenState.update {
                        MainSearchScreenState.Content(
                            offers = mapToOfferDelegateItems(result.offers),
                            vacancies = mapToVacancyDelegateItems(result.vacancies.take(VACANCY_COUNT_TO_SHOW))
                        )
                    }
                }.onFailure {
                    screenState.update { MainSearchScreenState.Error }
                }
        }
    }

    fun toggleVacancyFavoriteStatus(vacancyId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val vacancyList = (screenState.value as MainSearchScreenState.Content)
                    .vacancies
                    .toMutableList()

                repository.getVacancyById(vacancyId).onSuccess { vacancy ->
                    val newVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
                    repository.saveVacancyInCache(newVacancy)
                    val index = vacancyList.indexOfFirst { (it.id() as String) == newVacancy.id }
                    if (index != -1) {
                        vacancyList[index] = mapToVacancyDelegateItems(listOf(newVacancy)).first()
                    }
                    screenState.update { oldState ->
                        MainSearchScreenState.Content(
                            offers = (oldState as MainSearchScreenState.Content).offers,
                            vacancies = vacancyList
                        )
                    }
                }
            }
        }
    }

    fun syncVacanciesWithCache() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                if (screenState.value is MainSearchScreenState.Content) {
                    val oldVacancyList = (screenState.value as MainSearchScreenState.Content)
                        .vacancies
                        .toMutableList()
                    val newVacancyList = mutableListOf<Vacancy>()
                    oldVacancyList.forEach { vacancy ->
                        repository.getVacancyById(vacancy.id() as String).onSuccess {
                            newVacancyList.add(it)
                        }
                    }
                    screenState.update { oldState ->
                        MainSearchScreenState.Content(
                            offers = (oldState as MainSearchScreenState.Content).offers,
                            vacancies = mapToVacancyDelegateItems(newVacancyList)
                        )
                    }
                }
            }
        }
    }
}