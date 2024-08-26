package com.pavelshelkovenko.data.di

import com.pavelshelkovenko.data.OffersAndVacanciesRepository
import com.pavelshelkovenko.data.OffersAndVacanciesRepositoryImpl
import com.pavelshelkovenko.data.mapper.Mapper
import org.koin.dsl.module


val dataModule = module {
    single<OffersAndVacanciesRepository> {
        OffersAndVacanciesRepositoryImpl(
            apiService = get(),
            mapper = get()
        )
    }
    single<Mapper> { Mapper() }
}