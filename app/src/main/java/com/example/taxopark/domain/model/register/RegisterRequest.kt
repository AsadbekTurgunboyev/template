package com.example.taxopark.domain.model.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("phoneNumber") val phone: String
)
