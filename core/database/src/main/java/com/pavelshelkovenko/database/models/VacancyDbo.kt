package com.pavelshelkovenko.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pavelshelkovenko.database.AppDatabase

@Entity(tableName = AppDatabase.VACANCY_TABLE_NAME)
data class VacancyDbo(
    @PrimaryKey
    val id: String,
    val lookingNumber: Int,
    val title: String,
    @Embedded val address: AddressDbo,
    val company: String,
    @Embedded val experience: ExperienceDbo,
    val publishedDate: String,
    val isFavorite: Boolean,
    @Embedded val salary: SalaryDbo,
    val schedules: String,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String> = emptyList()
)