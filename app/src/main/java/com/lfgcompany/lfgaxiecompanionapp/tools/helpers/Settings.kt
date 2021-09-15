package com.lfgcompany.lfgaxiecompanionapp.tools.helpers

import android.content.SharedPreferences
import javax.inject.Inject

class Settings @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun save(key: String, value: Boolean) {
        val edit = sharedPreferences.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun saveDate(key: String, value: Long) {
        val edit = sharedPreferences.edit()
        edit.putLong(key, value)
        edit.apply()
    }

    fun getDate(key: String): Long {
        return sharedPreferences.getLong(key, 1L)
    }

    fun get(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}