package com.pavelshelkovenko.network

import com.pavelshelkovenko.network.models.OffersAndVacanciesDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getOffersAndVacancies() : Response<OffersAndVacanciesDto>
}