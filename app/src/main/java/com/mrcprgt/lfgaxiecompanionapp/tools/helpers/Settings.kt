package com.mrcprgt.lfgaxiecompanionapp.tools.helpers

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

    fun get(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}