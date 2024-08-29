package com.pavelshelkovenko.data.repository

import com.pavelshelkovenko.data.mapper.Mapper
import com.pavelshelkovenko.database.dao.VacancyDao
import com.pavelshelkovenko.domain.models.OffersAndVacancies
import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.domain.repository.OffersAndVacanciesRepository
import com.pavelshelkovenko.network.ApiException
import com.pavelshelkovenko.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class OffersAndVacanciesRepositoryImpl(
    private val apiService: ApiService,
    private val vacancyDao: VacancyDao,
    private val mapper: Mapper,
) : OffersAndVacanciesRepository {

    override suspend fun getOffersAndVacancies(isForceUpdate: Boolean): Result<OffersAndVacancies> {
        return try {
            val response = apiService.getOffersAndVacancies()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val result = mapper.mapOffersAndVacanciesDtoToDomain(body)
                    saveVacanciesToDatabase(result.vacancies)
                    Result.success(result)
                } else {
                    Result.failure(ApiException("http exception: empty body"))
                }
            } else {
                Result.failure(ApiException("http exception: code=${response.code()}, message=${response.message()}"))
            }
        } catch (exception: Exception) { Result.failure(exception) }
    }

    override suspend fun getVacancyById(vacancyId: String): Result<Vacancy> {
        val vacancyDbo = vacancyDao.getVacancyById(vacancyId)
        return if(vacancyDbo != null) {
            Result.success(mapper.mapVacancyDboToVacancyDomain(vacancyDbo))
        } else {
            Result.failure(IllegalStateException("No vacancy with given id was found"))
        }
    }

    override suspend fun getVacanciesFromCache(): List<Vacancy> {
        val vacanciesDbo = vacancyDao.getAllVacancies()
        return vacanciesDbo.map { vacancyDbo ->
            mapper.mapVacancyDboToVacancyDomain(vacancyDbo)
        }
    }

    override suspend fun getFavoriteVacancies(): List<Vacancy> {
        val vacanciesDbo = vacancyDao.getFavoriteVacancies()
        return vacanciesDbo.map { vacancyDbo ->
            mapper.mapVacancyDboToVacancyDomain(vacancyDbo)
        }
    }

    override suspend fun getFavoriteVacanciesFlow(): Flow<List<Vacancy>> {
        return vacancyDao.getFavouritesFlow().map {
            vacancyDboList -> vacancyDboList.map { vacancyDbo ->
                mapper.mapVacancyDboToVacancyDomain(vacancyDbo)
            }
        }
    }

    override suspend fun saveVacancyInCache(vacancy: Vacancy) {
        vacancyDao.insertVacancy(mapper.mapVacancyDomainToVacancyDbo(vacancy))
    }

    private suspend fun saveVacanciesToDatabase(vacancies: List<Vacancy>) {
        vacancies.forEach { vacancy ->
            vacancyDao.insertVacancy(
                vacancyDbo = mapper.mapVacancyDomainToVacancyDbo(vacancy)
            )
        }
    }
}