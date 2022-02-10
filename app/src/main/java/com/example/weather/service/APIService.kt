package com.example.weather.service

class APIService {  //lam trung gian ket hop Dataservice voi APIRetrofitClient
    //lam trung gian ket hop Dataservice voi APIRetrofitClient
    companion object {
        val base_url = "http://api.openweathermap.org/"
        fun getService(): Dataservice? {
            return APIRetrofitClient.getClient(base_url)?.create(Dataservice::class.java)
        }
    }

}