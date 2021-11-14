package com.app.chic_ecommerce.loginactivity.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.common.data.entities.User
import com.app.chic_ecommerce.loginactivity.data.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivityViewModel (private val loginRepository: LoginRepository)
    : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user: MutableLiveData<User> = MutableLiveData()
    var mail: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    val onError: MutableLiveData<String> = MutableLiveData()
    var onLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    fun validateLogin(): Boolean {
        if(mail.value == null || mail.value!! == ""){
            onError.postValue("mail is empty")
            return false
        }
        if(password.value == null || password.value!! == ""){
            onError.postValue("password is empty")
            return false
        }
        return true
    }
    fun login(){
        loginRepository.fetchLogin(mail.value!!, password.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.code == 200){
                    token.postValue(it.token)
                    user.postValue(User(it.user.username, it.user.email, it.user.phone))
                    onLoggedIn.postValue(true)
                }else{
                    onError.postValue(it.message)
                }
            }, {
                onError.postValue(it.localizedMessage)
            })
    }
}