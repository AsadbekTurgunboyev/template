package com.example.taxopark.domain.preference

import android.content.Context
import androidx.preference.PreferenceManager

class UserPreferenceManager(private val context: Context) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)


    /**
     * For example
     */
//    fun saveIsFirst(isFirst: Boolean) {
//        prefs.edit().putBoolean("isFirst", isFirst).apply()
//    }
//    fun getIsFirst(): Boolean = prefs.getBoolean("isFirst",false);
}