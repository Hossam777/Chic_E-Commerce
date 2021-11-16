package com.app.chic_ecommerce.mainactivity.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.common.data.entities.User
import com.app.chic_ecommerce.mainactivity.data.CheckTokenRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel (private val checkTokenRepository: CheckTokenRepository): ViewModel() {
    val onError: MutableLiveData<String> = MutableLiveData()
    val onLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val user: MutableLiveData<User> = MutableLiveData()

    fun checkToken(token: String) {
        onLoading.postValue(true)
        checkTokenRepository.fetchCheckToken(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onLoading.postValue(false)
                if(it.code == 200){
                    user.postValue(User(it.user.username, it.user.email, it.user.phone))
                }else{
                    onError.postValue("Token Is Invalid")
                }
            }, {
                onLoading.postValue(false)
                onError.postValue(it.localizedMessage)
            })
    }

}