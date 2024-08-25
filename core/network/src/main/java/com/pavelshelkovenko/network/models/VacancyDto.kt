package com.pavelshelkovenko.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VacancyDto(
    @SerialName("id") var id: String? = null,
    @SerialName("lookingNumber") var lookingNumber: Int? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("address") var address: Address? = null,
    @SerialName("company") var company: String? = null,
    @SerialName("experience") var experience: Experience? = null,
    @SerialName("publishedDate") var publishedDate: String? = null,
    @SerialName("isFavorite") var isFavorite: Boolean? = null,
    @SerialName("salary") var salary: Salary? = null,
    @SerialName("schedules") var schedules: List<String>? = null,
    @SerialName("appliedNumber") var appliedNumber: Int? = null,
    @SerialName("description") var description: String? = null,
    @SerialName("responsibilities") var responsibilities: String? = null,
    @SerialName("questions") var questions: List<String>? = null
)

@Serializable
class Address (
    @SerialName("town") val town: String? = null,
    @SerialName("street") val street: String? = null,
    @SerialName("house") val house: String? = null,
)

@Serializable
class Experience (
    @SerialName("previewText") val previewText: String? = null,
    @SerialName("text") val text: String? = null
)

@Serializable
class Salary (
    @SerialName("short") val short: String? = null,
    @SerialName("full") val full: String? = null,
)