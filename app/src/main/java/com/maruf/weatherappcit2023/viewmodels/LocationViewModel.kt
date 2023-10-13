package com.maruf.weatherappcit2023.viewmodels

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruf.weatherappcit2023.model.CurrentModel
import com.maruf.weatherappcit2023.repos.WeatherRepository
import kotlinx.coroutines.launch

class LocationViewModel:ViewModel() {
    private val repository = WeatherRepository()
    var locationLiveData:MutableLiveData<Location> = MutableLiveData()
    var currentModelLD:MutableLiveData<CurrentModel> = MutableLiveData()
    //var forecastModelLD:MutableLiveData<ForecastModel> = MutableLiveData()

    fun setNewLocations(location: Location){
        locationLiveData.value = location
    }
    fun fetchData(){
        viewModelScope.launch {
            try {
                currentModelLD.value =repository.fetchCurrentWeatherData(locationLiveData.value!!)
                //forecastModelLD.value =repository.fetchForecastWeatherData(locationLiveData.value!!)
            }catch (e:Exception){
                e.localizedMessage?.let { Log.e("LocationViewModel", it) }

            }
        }


    }
}