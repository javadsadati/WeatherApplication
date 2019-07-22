package com.example.weathermvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weathermvvm.data.db.entity.CURRENT_WEATHER_ID
import com.example.weathermvvm.data.db.entity.CurrentWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.current.ImperialCurrentWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}