package com.pavelshelkovenko.domain.repository

import com.pavelshelkovenko.domain.models.OffersAndVacancies
import com.pavelshelkovenko.domain.models.Vacancy
import kotlinx.coroutines.flow.Flow


interface OffersAndVacanciesRepository {

    suspend fun getOffersAndVacancies(isForceUpdate: Boolean = true) : Result<OffersAndVacancies>

    suspend fun getVacancyById(vacancyId: String) : Result<Vacancy>

    suspend fun getVacanciesFromCache(): List<Vacancy>

    suspend fun getFavoriteVacancies(): List<Vacancy>

    suspend fun getFavoriteVacanciesFlow(): Flow<List<Vacancy>>

    suspend fun saveVacancyInCache(vacancy: Vacancy)
}