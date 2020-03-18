package com.weather.forecast.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class Preferences(private val context: Context) {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        setup()
    }

    private fun setup() {
        sharedPreferences = context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)
    }

    fun setLastTimeStamp(key: String){
        editor = sharedPreferences.edit()
        editor.putLong(key, Calendar.getInstance().timeInMillis)
        editor.apply()
    }

    fun getLastTimeStamp(key: String): Long{
        return sharedPreferences.getLong(key, 0L)
    }
}