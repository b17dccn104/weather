package com.example.weather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherCurrent(
        var name: String,
        var weather: List<Weather>,
        var main: Main,
        var wind: Wind
) : Parcelable {

}