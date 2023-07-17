package com.example.proto_datastore_stevdaza_san

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ProtoRepository(application)

    val firstName = repository.readProto.asLiveData()

    fun updateValue(firstName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateValue(firstName)
    }
}