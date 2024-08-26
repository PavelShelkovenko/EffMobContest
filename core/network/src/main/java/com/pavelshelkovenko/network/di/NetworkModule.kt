package com.pavelshelkovenko.network.di

import com.pavelshelkovenko.network.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(get())
            .client(get())
            .build()
    }
    single<Converter.Factory> {
        Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY }
            ).build()
    }
}