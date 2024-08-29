package com.pavelshelkovenko.effmobcontest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: OffersAndVacanciesRepository
) : ViewModel() {

    var favouriteCount = MutableStateFlow(0)
        private set

    init {
        viewModelScope.launch {
            repository.getFavoriteVacanciesFlow()
                .onEach {
                    favouriteCount.value = it.size
                }
                .launchIn(viewModelScope)
        }
    }
}