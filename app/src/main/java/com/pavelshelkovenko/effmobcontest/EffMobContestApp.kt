package com.pavelshelkovenko.effmobcontest

import android.app.Application
import com.pavelshelkovenko.data.di.dataModule
import com.pavelshelkovenko.database.di.databaseModule
import com.pavelshelkovenko.effmobcontest.di.appModule
import com.pavelshelkovenko.feature_favorite_vacancies.di.favoriteVacanciesModule
import com.pavelshelkovenko.feature_login.di.loginModule
import com.pavelshelkovenko.feature_search_vacancies.di.searchVacanciesModule
import com.pavelshelkovenko.feature_vacancy_details.di.vacancyDetailsModule
import com.pavelshelkovenko.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EffMobContestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EffMobContestApp)
            modules(
                appModule,
                dataModule,
                networkModule,
                databaseModule,
                loginModule,
                searchVacanciesModule,
                favoriteVacanciesModule,
                vacancyDetailsModule,
            )
        }
    }
}