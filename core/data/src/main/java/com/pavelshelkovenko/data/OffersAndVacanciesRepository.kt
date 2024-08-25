package com.pavelshelkovenko.data

import com.pavelshelkovenko.data.models.OffersAndVacancies

interface OffersAndVacanciesRepository {
    suspend fun getOffersAndVacancies() : Result<OffersAndVacancies>
}