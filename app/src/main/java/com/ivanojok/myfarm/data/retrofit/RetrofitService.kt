package com.ivanojok.myfarm.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    fun createResponseService(): ResponseInterface {
        val retrofit = Retrofit.Builder().baseUrl("https://ivansfarm.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ResponseInterface::class.java)
        return service
    }
}