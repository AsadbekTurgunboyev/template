package com.example.taxopark.data.repository

import com.example.taxopark.data.source.ApiService
import com.example.taxopark.domain.model.register.RegisterData
import com.example.taxopark.domain.model.register.RegisterRequest
import com.example.taxopark.domain.repository.MainRepository

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {
    override suspend fun register(phone: RegisterRequest): RegisterData {
        return apiService.register(phone)
    }

}