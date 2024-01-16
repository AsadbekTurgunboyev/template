package com.example.taxopark.data.source

import com.example.taxopark.domain.model.car.CarData
import com.example.taxopark.domain.model.color.ColorData
import com.example.taxopark.domain.model.register.RegisterData
import com.example.taxopark.domain.model.register.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("driver/auth")
    suspend fun register(@Body phone: RegisterRequest): RegisterData

    @GET("car/all")
    suspend fun getAllCar(): List<CarData>

    @GET("color/all")
    suspend fun getAllColor(): List<ColorData>

}