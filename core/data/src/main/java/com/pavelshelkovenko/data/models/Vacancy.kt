package com.pavelshelkovenko.data.models

class Vacancy(
    var id: String,
    var lookingNumber: Int,
    var title: String,
    var address: Address,
    var company: String,
    var experience: Experience,
    var publishedDate: String,
    var isFavorite: Boolean,
    var salary: Salary,
    var schedules: List<String> = arrayListOf(),
    var appliedNumber: Int,
    var description: String,
    var responsibilities: String,
    var questions: List<String> = arrayListOf()
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