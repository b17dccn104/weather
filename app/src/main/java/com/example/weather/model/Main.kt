package com.example.weather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Main(
        var temp: Float,
        var feels_like: Float,
        var humidity: Float
) : Parcelable{
}