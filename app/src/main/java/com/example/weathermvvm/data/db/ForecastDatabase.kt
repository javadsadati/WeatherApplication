package com.example.weathermvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weathermvvm.data.db.entity.CurrentWeatherEntry
import com.example.weathermvvm.data.db.entity.FutureWeatherEntry
import com.example.weathermvvm.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class,WeatherLocation::class,FutureWeatherEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao(): FutureWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object {
        @Volatile private var instance: ForecastDatabase?=null
        private val lock= Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java,"forecastDayContainer.db")
                .build()
    }
}