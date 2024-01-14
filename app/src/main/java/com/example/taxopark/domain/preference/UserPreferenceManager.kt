package com.example.taxopark.domain.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class UserPreferenceManager(private val context: Context) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    companion object{
         const val STEP_DATA = "step_data"
    }

    fun updateStepData(data: Int){
        prefs.edit().putInt(STEP_DATA,data).apply()
    }

    fun saveToken(token: String) {
        prefs.edit().putString("_token", token).apply()
    }

    fun savePhone(phone: String) {
        prefs.edit().putString("phone", phone).apply()
    }
    fun getPhone(): String? {
        return prefs.getString("phone", null)
    }


    fun getSharedPreferences(): SharedPreferences {
        return prefs
    }
}