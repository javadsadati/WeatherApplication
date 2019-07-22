package com.example.weathermvvm.data.network.response

import com.example.weathermvvm.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDayContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)