package com.example.weathermvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weathermvvm.data.db.entity.WEATHER_LOCATION_ID
import com.example.weathermvvm.data.db.entity.WeatherLocation

@Dao
interface WeatherLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: WeatherLocation)

    @Query("select * from weather_location where id= $WEATHER_LOCATION_ID")
    fun getLocation(): LiveData<WeatherLocation>

    @Query("select * from weather_location where id= $WEATHER_LOCATION_ID")
    fun getLocationNonLive(): WeatherLocation?
}