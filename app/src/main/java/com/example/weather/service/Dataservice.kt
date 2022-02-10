package com.example.weather.service

import com.example.weather.model.WeatherCurrent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Dataservice {
    @GET("data/2.5/weather")
    fun getDataCurrentWeather(@Query("q") city: String,
                              @Query("appid") apiKey: String
    ) : Call<WeatherCurrent>
}