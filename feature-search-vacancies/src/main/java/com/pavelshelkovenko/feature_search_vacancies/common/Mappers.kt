package com.pavelshelkovenko.feature_search_vacancies.common

import com.pavelshelkovenko.domain.models.Offer
import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.feature_search_vacancies.main.OfferDelegateItem
import java.util.UUID

fun mapToOfferDelegateItems(offers: List<Offer>): List<OfferDelegateItem> {
    val delegateList = offers.map { offer ->
        OfferDelegateItem(
            id = offer.id ?: UUID.randomUUID().toString(),
            value = offer
        )
    }
    return delegateList
}

fun mapToVacancyDelegateItems(vacancies: List<Vacancy>): List<VacancyDelegateItem> {
    val delegateList = vacancies.map { vacancy ->
        VacancyDelegateItem(
            id = vacancy.id,
            value = vacancy
        )
    }
    return delegateList
}