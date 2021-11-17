package com.app.chic_ecommerce.cartfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.cartfragment.data.AddOrderRepository
import com.app.chic_ecommerce.common.data.entities.CartProduct
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartFragmentViewModel(private val addOrderRepository: AddOrderRepository)
    : ViewModel() {
    var promoCode: MutableLiveData<String> = MutableLiveData("")
    var totalPrice: MutableLiveData<Double> = MutableLiveData(0.0)
    var onError: MutableLiveData<String> = MutableLiveData()
    var onLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var onDataPosted: MutableLiveData<String> = MutableLiveData()
    fun addOrder(token: String, address: String, cartProducts: MutableList<CartProduct>) {
        totalPrice.value?.let { totalPrice ->
            promoCode.value?.let { promoCode ->
                onLoading.postValue(true)
                addOrderRepository.fetchAddOrder(token,promoCode, address, totalPrice, cartProducts)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if(it.code == 200){
                            onDataPosted.postValue(it.message)
                        }else{
                            onError.postValue(it.message)
                            println(it.errors)
                        }
                        onLoading.postValue(false)
                    }, {
                        onError.postValue(it.localizedMessage)
                        onLoading.postValue(false)
                        println(it.localizedMessage)
                    })

            }
        }
    }
}