package com.app.chic_ecommerce.signupactivity.presentation

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.common.data.entities.User

class SignupActivityViewModel: ViewModel() {
    var username: MutableLiveData<String> = MutableLiveData()
    var mail: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var repeatPassword: MutableLiveData<String> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()
    fun signup(): Boolean {
        if(username.value == null || username.value!! == ""){
            onError.postValue("Username is empty")
            return false
        }
        if(mail.value == null || mail.value!! == ""){
            onError.postValue("Email is empty")
            return false
        }
        if(password.value == null || password.value!! == ""){
            onError.postValue("Password is empty")
            return false
        }
        if(password.value != repeatPassword.value){
            onError.postValue("Password & Repeat Password not equal")
            return false
        }
        return true
    }
}