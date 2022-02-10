package com.example.weather.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class APIRetrofitClient {
    //khởi tạo retrofit để tương tác với server
//    private var retrofit: Retrofit? = null
    companion object{
        var retrofit: Retrofit? = null
        fun getClient(base_url: String?): Retrofit? { //truyền url để cấu hình được retrofit
            val okHttpClient = OkHttpClient.Builder() // tương tác mạng với server
                .readTimeout(100000, TimeUnit.MILLISECONDS)
                .writeTimeout(100000, TimeUnit.MILLISECONDS)
                .connectTimeout(100000, TimeUnit.MILLISECONDS) //ngắt kết nối nếu quá 10s
                .retryOnConnectionFailure(true) //cố kết nối khi mạng lỗi
                .build()

            //Convert json sang biến của java
            val gson = GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
        }
    }

}
