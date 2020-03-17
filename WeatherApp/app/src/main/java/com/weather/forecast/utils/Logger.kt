package com.weather.forecast.utils

import android.util.Log

object Logger {
    fun error(tag: String?, message: String?, exception: Exception?) {
        Log.e(tag, message + exception?.localizedMessage)
    }

    fun error(tag: String?, message: String?, errorCode: Int?) {
        Log.e(tag, message + errorCode)
    }

    fun error(tag: String?, message: String?, errorCode: String?) {
        Log.e(tag, message + errorCode)
    }

    fun info(tag: String?, message: String?, data: String?) {
        Log.d(tag, message + data)
    }
}