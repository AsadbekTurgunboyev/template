package com.example.taxopark.presentation.login.phone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taxopark.domain.model.register.RegisterData
import com.example.taxopark.domain.model.register.RegisterRequest
import com.example.taxopark.domain.usecase.GetMainResponseUseCase
import com.example.taxopark.utils.resource.Resource
import com.example.taxopark.utils.resource.ResourceState
import kotlinx.coroutines.launch

class InputPhoneViewModel(private val mainResponseUseCase: GetMainResponseUseCase): ViewModel() {


    private var _registerResponse = MutableLiveData<Resource<RegisterData>>()
    val registerResponse : LiveData<Resource<RegisterData>> get() = _registerResponse


    fun register(phone: RegisterRequest){
        _registerResponse.value = Resource(ResourceState.LOADING)
        viewModelScope.launch {
            try {
                val response = mainResponseUseCase.register(phone = phone)
                Log.d("tekshirish", "register: $response")
                _registerResponse.value = Resource(ResourceState.SUCCESS, response, null)
            } catch (e: Exception) {
                val errorMessage = e.message
                Log.e("tekshirish", "register: $errorMessage")

                _registerResponse.value = Resource(ResourceState.ERROR, message = errorMessage)
            }
        }
    }

    fun clear() {
        _registerResponse = MutableLiveData()
    }
}