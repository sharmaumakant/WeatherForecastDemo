package com.weather.forecast.utils

import android.util.Log

/**
 * Logger class.
 * Used for logging error, exception, information
 */
object Logger {
    /**
     * @param tag
     * @param message
     * @param exception
     */
    fun error(tag: String?, message: String?, exception: Exception?) {
        Log.e(tag, message + exception?.localizedMessage)
    }

    /**
     * @param tag
     * @param message
     * @param exception
     */
    fun error(tag: String?, message: String?, errorCode: Int?) {
        Log.e(tag, message + errorCode)
    }

    /**
     * @param tag
     * @param message
     * @param errorCode
     */
    fun error(tag: String?, message: String?, errorCode: String?) {
        Log.e(tag, message + errorCode)
    }

    /**
     * @param tag
     * @param message
     * @param data
     */
    fun info(tag: String?, message: String?, data: String?) {
        Log.d(tag, message + data)
    }
}