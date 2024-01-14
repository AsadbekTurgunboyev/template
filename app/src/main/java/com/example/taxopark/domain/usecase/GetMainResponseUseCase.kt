package com.example.taxopark.domain.usecase

import com.example.taxopark.domain.model.register.RegisterRequest
import com.example.taxopark.domain.repository.MainRepository

class GetMainResponseUseCase(private val mainRepository: MainRepository) {


    suspend fun register(phone: RegisterRequest) = mainRepository.register(phone = phone)
}