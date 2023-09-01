package com.example.taxopark

import android.app.Application
import com.example.taxopark.di.NetworkModule
import com.example.taxopark.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(listOf(koinModule, NetworkModule)) //<- this add next module
        }
    }


}