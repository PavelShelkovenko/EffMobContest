package com.pavelshelkovenko.feature_vacancy_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class VacancyDetailsViewModel(
    private val repository: OffersAndVacanciesRepository
): ViewModel() {

    var screenState = MutableStateFlow(VacancyDetailsScreenState.Initial as VacancyDetailsScreenState)
        private set

    fun loadData(vacancyId: String) {
        viewModelScope.launch {
            val result = repository.getVacancyById(vacancyId)
            val questionDelegateItems = mutableListOf<QuestionDelegateItem>()
            result.onSuccess { vacancy ->
                vacancy.questions.forEach { question ->
                    questionDelegateItems.add(
                        QuestionDelegateItem(
                            id = UUID.randomUUID().toString(),
                            value = question
                        )
                    )
                }
                screenState.update {
                    VacancyDetailsScreenState.Content(
                        vacancy = vacancy,
                        questions = questionDelegateItems
                    )
                }
            }.onFailure {
                screenState.update { VacancyDetailsScreenState.Error }
            }
        }
    }

    fun toggleVacancyFavoriteStatus(vacancyId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                repository.getVacancyById(vacancyId).onSuccess { vacancy ->
                    val newVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
                    repository.saveVacancyInCache(newVacancy)
                    screenState.update { oldState ->
                        VacancyDetailsScreenState.Content(
                            questions = (oldState as VacancyDetailsScreenState.Content).questions,
                            vacancy = newVacancy
                        )
                    }
                }.onFailure {
                    screenState.update { VacancyDetailsScreenState.Error }
                }
            }
        }
    }
}