package com.pavelshelkovenko.data.mapper

import com.pavelshelkovenko.data.models.Address
import com.pavelshelkovenko.data.models.Experience
import com.pavelshelkovenko.data.models.Offer
import com.pavelshelkovenko.data.models.OffersAndVacancies
import com.pavelshelkovenko.data.models.Salary
import com.pavelshelkovenko.data.models.Vacancy
import com.pavelshelkovenko.network.models.OfferDto
import com.pavelshelkovenko.network.models.OffersAndVacanciesDto
import com.pavelshelkovenko.network.models.VacancyDto
import java.util.Locale
import java.util.UUID

class Mapper {
    fun mapOffersAndVacanciesDtoToDomain(
        offersAndVacanciesDto: OffersAndVacanciesDto
    ): OffersAndVacancies {
        val offers = offersAndVacanciesDto.offers.map(::mapOfferDtoToDomain)
        val vacancies = offersAndVacanciesDto.vacancies.map(::mapVacancyFromDtoToDomain)

        return OffersAndVacancies(offers = offers, vacancies = vacancies)
    }

    private fun mapVacancyFromDtoToDomain(vacancyDto: VacancyDto): Vacancy {
        return Vacancy(
            id = vacancyDto.id ?: UUID.randomUUID().toString(),
            lookingNumber = vacancyDto.lookingNumber ?: 0,
            title = vacancyDto.title.orEmpty(),
            address = Address(
                town = vacancyDto.address?.town.orEmpty(),
                street = vacancyDto.address?.street.orEmpty(),
                house = vacancyDto.address?.house.orEmpty()
            ),
            company = vacancyDto.company.orEmpty(),
            experience = Experience(
                previewText = vacancyDto.experience?.previewText.orEmpty(),
                text = vacancyDto.experience?.text.orEmpty()
            ),
            publishedDate = vacancyDto.publishedDate.orEmpty(),
            isFavorite = vacancyDto.isFavorite ?: false,
            salary = Salary(
                short = vacancyDto.salary?.short.orEmpty(),
                full = vacancyDto.salary?.full.orEmpty()
            ),
            schedules = vacancyDto.schedules
                ?.joinToString(",")
                ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }.orEmpty() ,
            appliedNumber = vacancyDto.appliedNumber ?: 0,
            description = vacancyDto.description.orEmpty(),
            responsibilities = vacancyDto.responsibilities.orEmpty(),
            questions = vacancyDto.questions ?: emptyList(),
        )
    }

    private fun mapOfferDtoToDomain(offerDto: OfferDto): Offer {
        return Offer(
            id = offerDto.id,
            title = offerDto.title.orEmpty(),
            link = offerDto.link.orEmpty(),
            buttonTitle = offerDto.button?.text.orEmpty()
        )
    }
}