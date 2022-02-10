package com.example.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.Adapter.MainViewAdapter
import com.example.weather.Fragment.Fragment_Home
import com.example.weather.Fragment.Fragment_Weather_7Day
import com.example.weather.service.APIService
import com.example.weather.service.Dataservice
import com.example.weather.model.WeatherCurrent
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setUpView()
    }

    private fun setUpView() {
        val adapter = MainViewAdapter(supportFragmentManager)
        adapter.addFragment(Fragment_Home(), "Home")
        adapter.addFragment(Fragment_Weather_7Day(), "Next Day")
        myViewPager.adapter = adapter
        myTabLayout.setupWithViewPager(myViewPager)
        myTabLayout.getTabAt(0)?.setIcon(R.drawable.ic_baseline_home_24)
        myTabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_next_week_24)
    }


}

