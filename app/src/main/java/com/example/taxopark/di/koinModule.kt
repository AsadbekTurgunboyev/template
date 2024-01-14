package com.example.taxopark.di

import com.example.taxopark.data.repository.MainRepositoryImpl
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.domain.repository.MainRepository
import com.example.taxopark.domain.usecase.GetMainResponseUseCase
import com.example.taxopark.presentation.login.phone.InputPhoneViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    factory { UserPreferenceManager(androidContext()) }
    factory { GetMainResponseUseCase(get()) }
    single<MainRepository>{
        MainRepositoryImpl(apiService = get())
    }

    viewModel{ InputPhoneViewModel(get())}
}