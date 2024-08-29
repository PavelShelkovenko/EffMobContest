package com.pavelshelkovenko.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pavelshelkovenko.database.AppDatabase
import com.pavelshelkovenko.database.models.VacancyDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyDbo: VacancyDbo)

    @Query("SELECT * FROM ${AppDatabase.VACANCY_TABLE_NAME} ORDER BY publishedDate")
    suspend fun getAllVacancies(): List<VacancyDbo>

    @Query("SELECT * FROM ${AppDatabase.VACANCY_TABLE_NAME} WHERE isFavorite = 1")
    suspend fun getFavoriteVacancies(): List<VacancyDbo>

    @Query("SELECT * FROM ${AppDatabase.VACANCY_TABLE_NAME} WHERE isFavorite = 1")
    fun getFavouritesFlow(): Flow<List<VacancyDbo>>

    @Query("SELECT * FROM ${AppDatabase.VACANCY_TABLE_NAME} WHERE id LIKE :id LIMIT 1")
    suspend fun getVacancyById(id: String): VacancyDbo?

    @Delete
    suspend fun deleteVacancy(vacancyDbo: VacancyDbo)
}