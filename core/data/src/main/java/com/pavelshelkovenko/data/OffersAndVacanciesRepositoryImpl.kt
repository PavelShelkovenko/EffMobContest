package com.pavelshelkovenko.data

import com.pavelshelkovenko.data.mapper.Mapper
import com.pavelshelkovenko.data.models.OffersAndVacancies
import com.pavelshelkovenko.network.ApiException
import com.pavelshelkovenko.network.ApiService
import com.pavelshelkovenko.network.RetrofitInstance

class OffersAndVacanciesRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: Mapper,
) : OffersAndVacanciesRepository {

    override suspend fun getOffersAndVacancies(): Result<OffersAndVacancies> {
        val response = apiService.getOffersAndVacancies()
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.success(mapper.mapOffersAndVacanciesDtoToDomain(body))
            } else {
                Result.failure(ApiException("http exception: empty body"))
            }
        } else {
            Result.failure(ApiException("http exception: code=${response.code()}, message=${response.message()}"))
        }
    }
}

object Repo {
    fun getRepo(): OffersAndVacanciesRepository {
        return OffersAndVacanciesRepositoryImpl(
            RetrofitInstance.api,
            Mapper()
        )
    }
}