package com.example.weather.Fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import com.example.weather.R
import com.example.weather.model.WeatherCurrent
import com.example.weather.service.APIService
import com.example.weather.service.Dataservice
import kotlinx.android.synthetic.main.fragment__home.*
import kotlinx.android.synthetic.main.fragment__home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Fragment_Home : Fragment() {
    var textview_current_time: TextView? = null
    var button_search: Button? = null
    var edittext_search: EditText? = null
    var groud_search: Group? = null
    var group_city: Group? = null
    var image_add: ImageView? = null
    var textview_city: TextView? = null
    var textview_temperature: TextView?  = null
    var textview_temp_feels: TextView?  = null
    var textview_detail_humidity: TextView?  = null
    var textview_detail_windspeed: TextView?  = null
    var textview_description: TextView?  = null
    var imageview_icon_weather: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment__home, container, false)
        textview_current_time = view.findViewById(R.id.textview_current_time)
        button_search = view.findViewById(R.id.button_search)
        edittext_search = view.findViewById(R.id.edittext_search)
        groud_search = view.findViewById(R.id.groud_search)
        group_city = view.findViewById(R.id.group_city)
        image_add = view.findViewById(R.id.image_add)
        textview_city = view.findViewById(R.id.textview_city)
        textview_temperature = view.findViewById(R.id.textview_temperature)
        textview_temp_feels = view.findViewById(R.id.textview_temp_feels)
        textview_detail_windspeed = view.findViewById(R.id.textview_detail_windspeed)
        textview_detail_humidity = view.findViewById(R.id.textview_detail_humidity)
        textview_description = view.findViewById(R.id.textview_description)
        imageview_icon_weather = view.findViewById(R.id.imageview_icon_weather)


        setTimeAndDateCurrent()
        selectCity()
        searchCity()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTimeAndDateCurrent() {
        textview_current_time?.text = getTimeAnDateCurrent()
    }

    private fun searchCity() {
        button_search?.setOnClickListener {
            var city = edittext_search?.text.toString()
            getDataCurrentWeather(city)
            groud_search?.visibility = View.GONE
            group_city?.visibility = View.VISIBLE
            edittext_search?.text = null
        }
    }

    private fun selectCity() {
        image_add?.setOnClickListener {
            group_city?.visibility = View.GONE
            groud_search?.visibility = View.VISIBLE
        }
    }

    private fun getDataCurrentWeather(city: String){
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<WeatherCurrent>? = dataservice?.getDataCurrentWeather("$city", API_KEY)
        callback?.enqueue(object : Callback<WeatherCurrent?> {
            override fun onResponse(call: Call<WeatherCurrent?>, response: Response<WeatherCurrent?>) {
                var weatherCurrent = response.body()
                textview_city?.text = weatherCurrent?.name
                textview_temperature?.text = (weatherCurrent?.main?.temp?.toInt()?.minus(273)).toString()
                textview_temp_feels?.text = (weatherCurrent?.main?.feels_like?.toInt()?.minus(273)).toString()
                textview_detail_humidity?.text = weatherCurrent?.main?.humidity?.toInt()?.toString()
                textview_detail_windspeed?.text = weatherCurrent?.wind?.speed.toString()
                textview_description?.text = weatherCurrent?.weather?.get(0)?.description.toString()
                var icon = weatherCurrent?.weather?.get(0)?.icon.toString()
                imageview_icon_weather?.let {
                    LoadImageTask(it).execute(
                            "http://openweathermap.org/img/wn/"+icon+".png")
                }
            }
            override fun onFailure(call: Call<WeatherCurrent?>, t: Throwable) {
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeAnDateCurrent() : String? {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        return formatted
    }

    class LoadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>()
    {
        override fun doInBackground(vararg urls: String): Bitmap?
        {
            try {
                val stream = URL(urls[0]).openStream()

                return BitmapFactory.decodeStream(stream)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }
        override fun onPostExecute(bitmap: Bitmap?)
        {
            imageView.setImageBitmap(bitmap)
        }
    }

    companion object {
        const val API_KEY = "5667e18f3836363f6926165a15043cc5"
    }

}