package com.pavelshelkovenko.data.models

class Vacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: String,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String> = emptyList()
)

class Address (
    val town: String,
    val street: String,
    val house: String,
)

class Experience (
    val previewText: String,
    val text: String,
)

class Salary (
    val short: String,
    val full: String,
)