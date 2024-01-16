package com.example.taxopark.domain.model.car

data class CarData(

    val id: Int,
    val createdAt: String,
    val updatedAt: String,
    val name: String,
    val numberOfSeats: Int,
    val photo: String
)
