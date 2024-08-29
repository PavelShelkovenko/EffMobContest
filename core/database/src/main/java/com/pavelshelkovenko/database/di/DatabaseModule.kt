package com.pavelshelkovenko.database.di

import com.pavelshelkovenko.database.AppDatabase
import com.pavelshelkovenko.database.dao.VacancyDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(context = get()) }
    single<VacancyDao> { get<AppDatabase>().vacancyDao() }
}