package com.pavelshelkovenko.data.mapper

import com.pavelshelkovenko.domain.models.Address
import com.pavelshelkovenko.domain.models.Experience
import com.pavelshelkovenko.domain.models.Offer
import com.pavelshelkovenko.domain.models.OffersAndVacancies
import com.pavelshelkovenko.domain.models.Salary
import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.database.models.AddressDbo
import com.pavelshelkovenko.database.models.ExperienceDbo
import com.pavelshelkovenko.database.models.SalaryDbo
import com.pavelshelkovenko.database.models.VacancyDbo
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

        return OffersAndVacancies(
            offers = offers,
            vacancies = vacancies
        )
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
                ?.joinToString(", ")
                ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                .orEmpty(),
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

    fun mapVacancyDboToVacancyDomain(vacancyDbo: VacancyDbo): Vacancy {
        return Vacancy(
            id = vacancyDbo.id,
            lookingNumber = vacancyDbo.lookingNumber,
            title = vacancyDbo.title,
            address = Address(
                town = vacancyDbo.address.town,
                street = vacancyDbo.address.street,
                house = vacancyDbo.address.house
            ),
            company = vacancyDbo.company,
            experience = Experience(
                previewText = vacancyDbo.experience.previewText,
                text = vacancyDbo.experience.text
            ),
            publishedDate = vacancyDbo.publishedDate,
            isFavorite = vacancyDbo.isFavorite,
            salary = Salary(
                short = vacancyDbo.salary.short,
                full = vacancyDbo.salary.full
            ),
            schedules = vacancyDbo.schedules,
            appliedNumber = vacancyDbo.appliedNumber,
            description = vacancyDbo.description,
            responsibilities = vacancyDbo.responsibilities,
            questions = vacancyDbo.questions,
        )
    }

    fun mapVacancyDomainToVacancyDbo(vacancy: Vacancy): VacancyDbo {
        return VacancyDbo(
            id = vacancy.id,
            lookingNumber = vacancy.lookingNumber,
            title = vacancy.title,
            address = AddressDbo(
                town = vacancy.address.town,
                street = vacancy.address.street,
                house = vacancy.address.house
            ),
            company = vacancy.company,
            experience = ExperienceDbo(
                previewText = vacancy.experience.previewText,
                text = vacancy.experience.text
            ),
            publishedDate = vacancy.publishedDate,
            isFavorite = vacancy.isFavorite,
            salary = SalaryDbo(
                short = vacancy.salary.short,
                full = vacancy.salary.full
            ),
            schedules = vacancy.schedules,
            appliedNumber = vacancy.appliedNumber,
            description = vacancy.description,
            responsibilities = vacancy.responsibilities,
            questions = vacancy.questions,
        )
    }
}