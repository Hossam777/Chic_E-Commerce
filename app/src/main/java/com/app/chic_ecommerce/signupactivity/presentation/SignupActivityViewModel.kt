package com.app.chic_ecommerce.signupactivity.presentation

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.common.data.entities.User
import com.app.chic_ecommerce.signupactivity.data.SignupRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupActivityViewModel(private val signupRepository: SignupRepository)
    : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var username: MutableLiveData<String> = MutableLiveData()
    var mail: MutableLiveData<String> = MutableLiveData()
    var phone: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var repeatPassword: MutableLiveData<String> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()
    var onSignedUp: MutableLiveData<Boolean> = MutableLiveData()
    fun validateSignupData(): Boolean {
        if(username.value == null || username.value!! == ""){
            onError.postValue("Username is empty")
            return false
        }
        if(mail.value == null || mail.value!! == ""){
            onError.postValue("Email is empty")
            return false
        }
        if(phone.value == null || phone.value!!.length != 11){
            onError.postValue("Phone is wrong")
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
    fun signup() {
        signupRepository.fetchSignup(username.value!!, mail.value!!, password.value!!, phone.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.code == 200){
                    token.postValue(it.token)
                    onSignedUp.postValue(true)
                }else{
                    onError.postValue(it.message)
                }
            }, {
                onError.postValue(it.localizedMessage)
            })
    }
}