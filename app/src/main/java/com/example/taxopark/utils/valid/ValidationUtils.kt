package com.example.taxopark.utils.valid

object ValidationUtils {
    fun isValidPhoneNumber(phone: String): Boolean = phone.length == 12
}