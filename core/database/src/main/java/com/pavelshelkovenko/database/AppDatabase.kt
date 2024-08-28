package com.pavelshelkovenko.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pavelshelkovenko.database.dao.VacancyDao
import com.pavelshelkovenko.database.models.VacancyDbo

@Database(
    entities = [VacancyDbo::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vacancyDao(): VacancyDao

    companion object {

        const val VACANCY_TABLE_NAME = "vacancies"
        private const val DB_NAME = "effmobcontest.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}