package com.example.taxopark.domain.model.confirmcode

data class ConfirmCodeRequest(
    val otpId: String,
    val verificationCode: String
)
