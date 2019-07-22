package com.example.weathermvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.data.network.response.CurrentWeatherResponse
import com.example.weathermvvm.data.network.response.FutureWeatherResponse
import com.example.weathermvvm.internal.NoConnectivityException

const val FORECAST_DAYS_COUNT =7

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try{
            val fetchedCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location,languageCode)
                .await()

            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e: NoConnectivityException){
            Log.e("Connectivity","No Internet connection",e)
        }
    }


    override suspend fun fetchFutureWeather(location: String, languageCode: String) {
        try {
            val fetchFutureWeather = apixuWeatherApiService
                .getFutureWeather(location,FORECAST_DAYS_COUNT,languageCode)
                .await()
            _downloadedFutureWeather.postValue(fetchFutureWeather)
        }catch (e: NoConnectivityException){
            Log.e("Connectivity","No Internet connection",e)
        }
    }
}