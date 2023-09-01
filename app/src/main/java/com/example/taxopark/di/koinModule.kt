package com.example.taxopark.di

import com.example.taxopark.domain.preference.UserPreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val koinModule = module {
    factory { UserPreferenceManager(androidContext()) }

}