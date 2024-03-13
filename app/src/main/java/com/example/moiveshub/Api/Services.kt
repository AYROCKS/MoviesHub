package com.example.moiveshub.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Services {
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}