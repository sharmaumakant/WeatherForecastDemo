package com.weather.forecast.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database open helper class
 */
@Database(entities = [WeatherForecast::class], version = 1)
abstract class WeatherForecastDatabase : RoomDatabase() {

    abstract fun weatherForecastDao(): WeatherForecastDao

    companion object {
        @Volatile
        private var instance: WeatherForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context,
                WeatherForecastDatabase::class.java, "note_database.db"
            )
            .build()
    }
}