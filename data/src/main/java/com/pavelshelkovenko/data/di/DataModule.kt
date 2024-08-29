package com.pavelshelkovenko.data.di

import com.pavelshelkovenko.data.mapper.Mapper
import com.pavelshelkovenko.data.repository.OffersAndVacanciesRepositoryImpl
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import org.koin.dsl.module


val dataModule = module {
    single<OffersAndVacanciesRepository> {
        OffersAndVacanciesRepositoryImpl(
            apiService = get(),
            mapper = get(),
            vacancyDao = get(),
        )
    }
    single<Mapper> { Mapper() }
}