package com.maruf.weatherappcit2023.networks


import android.annotation.SuppressLint
import com.maruf.weatherappcit2023.model.CurrentModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.text.SimpleDateFormat
import java.util.*

//const val weather_api_key = "9849f3ff0c76e893f369a31e8931a130"
const val weather_api_key = "d98aea393d44d7912075d86400b9c419"
const val baseUrl = "https://api.openweathermap.org/data/2.5/"
const val icon_prefix = "https://openweathermap.org/img/wn/"
const val icon_suffix = "@2x.png"

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(dt:Long, pattern: String): String =
     SimpleDateFormat(pattern).format(Date(dt*1000))



val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface WeatherServiceApi{
    @GET
   suspend fun getCurrentWeatherData(@Url endUrl:String): CurrentModel
    /*@GET
   suspend fun getForecastWeatherData(@Url endUrl:String):ForecastModel*/
}

object NetworkService{
    val weatherServiceApi: WeatherServiceApi by lazy {
        retrofit.create(WeatherServiceApi::class.java)
    }
}