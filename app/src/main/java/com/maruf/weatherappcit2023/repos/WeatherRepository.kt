package com.maruf.weatherappcit2023.repos

import android.location.Location
import com.maruf.weatherappcit2023.model.CurrentModel
import com.maruf.weatherappcit2023.networks.NetworkService
import com.maruf.weatherappcit2023.networks.weather_api_key

class WeatherRepository{

    suspend fun fetchCurrentWeatherData(location: Location): CurrentModel {
        val endUrl = "weather?lat=${location.latitude}&lon=${location.longitude}&units=metric&appid=$weather_api_key"
       return NetworkService.weatherServiceApi.getCurrentWeatherData(endUrl)
    }
   /* suspend fun fetchForecastWeatherData(location: Location): ForecastModel{
        val endUrl = "forecast?lat=${location.latitude}&lon=${location.longitude}&units=metric&appid=$weather_api_key"
        return NetworkService.weatherServiceApi.getForecastWeatherData(endUrl)
    }*/
}