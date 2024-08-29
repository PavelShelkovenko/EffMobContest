package com.pavelshelkovenko.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OffersAndVacanciesDto(
    @SerialName("offers") val offers: List<OfferDto>,
    @SerialName("vacancies") val vacancies: List<VacancyDto>,
)





