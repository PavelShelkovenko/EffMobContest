package com.pavelshelkovenko.data

import com.pavelshelkovenko.data.models.OffersAndVacancies
import com.pavelshelkovenko.data.models.Vacancy

interface OffersAndVacanciesRepository {
    suspend fun getOffersAndVacancies(isForceUpdate: Boolean = true) : Result<OffersAndVacancies>

    suspend fun getVacancyById(vacancyId: String) : Result<Vacancy>
}