package com.example.weathermvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weathermvvm.data.db.entity.FutureWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.future.detail.ImperialDetailFutureWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.future.detail.MetricDetailFutureWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.future.list.ImperialSimpleFutureWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

@Dao
interface FutureWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries: List<FutureWeatherEntry>)

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastMetric(startDate: LocalDate): LiveData<List<MetricSimpleFutureWeatherEntry>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastImperial(startDate: LocalDate): LiveData<List<ImperialSimpleFutureWeatherEntry>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from future_weather where date(date) = date(:date)")
    fun getDetailedWeatherByDateMetric(date: LocalDate): LiveData<MetricDetailFutureWeatherEntry>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from future_weather where date(date) = date(:date)")
    fun getDetailedWeatherByDateImperial(date: LocalDate): LiveData<ImperialDetailFutureWeatherEntry>

    @Query("select count(id) from future_weather where date(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query("delete from future_weather where date(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)
}