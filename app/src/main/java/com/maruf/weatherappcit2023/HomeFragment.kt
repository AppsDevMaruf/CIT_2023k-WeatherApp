package com.maruf.weatherappcit2023

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.maruf.weatherappcit2023.databinding.FragmentHomeBinding
import com.maruf.weatherappcit2023.viewmodels.LocationViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val locationViewModel: LocationViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,null,false)
        locationViewModel.locationLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, "${it.latitude} ${it.longitude}")
            locationViewModel.fetchData()
        }

        locationViewModel.currentModelLD.observe(viewLifecycleOwner) {
            Log.e(TAG, "WeatherFragment: ,${it.main?.temp}")
            it?.let {
                binding.apply {
                    tempTV.text = "${it.main?.temp}°"
                    addressTV.text = "${it.name} ${it.sys?.country}"
                    binding.iconIV.load(it.weather?.get(0)?.icon)
                    binding.feelsLikeTV.text = "feel like ${it.main?.feelsLike} \u00B0"
                    binding.humidityTV.text = it.main?.humidity.toString()
                    binding.pressureTV.text = it.main?.pressure.toString()
                    binding.conditionTV.text = it.weather?.get(0)?.description.toString()
            }
        }



            }

            //binding.current =it



        return binding.root
    }

}