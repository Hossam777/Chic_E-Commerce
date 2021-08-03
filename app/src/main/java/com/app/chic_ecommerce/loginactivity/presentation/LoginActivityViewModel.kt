package com.app.chic_ecommerce.loginactivity.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginActivityViewModel: ViewModel() {
    var mail: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
}