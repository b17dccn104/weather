package com.example.weather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Weather(
        var description: String,
        var icon: String
) : Parcelable{
}