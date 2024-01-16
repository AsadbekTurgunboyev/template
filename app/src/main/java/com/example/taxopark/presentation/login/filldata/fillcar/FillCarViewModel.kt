package com.example.taxopark.presentation.login.filldata.fillcar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taxopark.domain.model.car.CarData
import com.example.taxopark.domain.model.color.ColorData
import com.example.taxopark.domain.usecase.GetMainResponseUseCase
import com.example.taxopark.utils.resource.Resource
import com.example.taxopark.utils.resource.ResourceState
import kotlinx.coroutines.launch

class FillCarViewModel(private val mainResponseUseCase: GetMainResponseUseCase): ViewModel() {
    private val _colorResponse = MutableLiveData<Resource<List<ColorData>>>()
    val colorResponse: LiveData<Resource<List<ColorData>>> get() = _colorResponse

    private val _carResponse = MutableLiveData<Resource<List<CarData>>>()
    val carResponse : LiveData<Resource<List<CarData>>> get() = _carResponse


    fun getAllColor(){
        _colorResponse.value = Resource(ResourceState.LOADING)
        viewModelScope.launch {
            try {
                val response = mainResponseUseCase.getAllColor()
                Log.d("tekshirish", "color: $response")
                _colorResponse.value = Resource(ResourceState.SUCCESS, response, null)
            } catch (e: Exception) {
                val errorMessage = e.message
                Log.e("tekshirish", "color: $errorMessage")

                _colorResponse.value = Resource(ResourceState.ERROR, message = errorMessage)
            }
        }
    }

    fun getAllCar(){
        _carResponse.value = Resource(ResourceState.LOADING)
        viewModelScope.launch {
            try {
                val response = mainResponseUseCase.getAllCar()
                Log.d("tekshirish", "car: $response")
                _carResponse.value = Resource(ResourceState.SUCCESS, response, null)
            } catch (e: Exception) {
                val errorMessage = e.message
                Log.e("tekshirish", "car: $errorMessage")

                _carResponse.value = Resource(ResourceState.ERROR, message = errorMessage)
            }
        }
    }
}