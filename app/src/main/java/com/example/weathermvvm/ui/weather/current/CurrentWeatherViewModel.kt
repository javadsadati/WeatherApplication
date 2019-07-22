package com.example.weathermvvm.ui.weather.current

import com.example.weathermvvm.data.provider.UnitProvider
import com.example.weathermvvm.data.repository.ForecastRepository
import com.example.weathermvvm.internal.lazyDeferred
import com.example.weathermvvm.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider) {

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather(isMetricUnit)
    }

}

