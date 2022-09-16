package com.example.composesample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel()  {

    private var name=MutableLiveData<String>()

    fun getName():LiveData<String> = name

    fun updateName(name: String){
        this.name.value=name
    }
    
}