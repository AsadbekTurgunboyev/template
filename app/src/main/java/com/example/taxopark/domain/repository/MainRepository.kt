package com.example.taxopark.domain.repository

import com.example.taxopark.domain.model.car.CarData
import com.example.taxopark.domain.model.color.ColorData
import com.example.taxopark.domain.model.register.RegisterData
import com.example.taxopark.domain.model.register.RegisterRequest

interface MainRepository {

    suspend fun register(phone: RegisterRequest) : RegisterData

    suspend fun getAllCar(): List<CarData>

    suspend fun getAllColor(): List<ColorData>
}