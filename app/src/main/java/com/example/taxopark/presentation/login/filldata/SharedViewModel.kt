package com.example.taxopark.presentation.login.filldata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _action = MutableLiveData<Unit>()
    val action: LiveData<Unit> get() = _action

    fun performAction() {
        _action.value = Unit
    }
}