package com.weather.forecast.model.network

import com.weather.forecast.utils.Constants.Base_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * singleton class for creating retrofit client
 */
object ApiClient {
    val apiClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}