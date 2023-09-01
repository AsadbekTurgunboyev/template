package com.example.taxopark.data.repository

import com.example.taxopark.data.source.ApiService
import com.example.taxopark.domain.repository.MainRepository

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {
}