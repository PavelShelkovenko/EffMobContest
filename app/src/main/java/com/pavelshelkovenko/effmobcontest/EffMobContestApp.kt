package com.pavelshelkovenko.effmobcontest

import android.app.Application
import com.pavelshelkovenko.data.di.dataModule
import com.pavelshelkovenko.feature_login.di.loginModule
import com.pavelshelkovenko.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EffMobContestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EffMobContestApp)
            modules(
                dataModule,
                networkModule,
                loginModule,
            )
        }
    }
}