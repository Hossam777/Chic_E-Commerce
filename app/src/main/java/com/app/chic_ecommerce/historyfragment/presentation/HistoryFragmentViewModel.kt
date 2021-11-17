package com.app.chic_ecommerce.historyfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.historyfragment.data.GetOrdersByUserRepository
import com.app.chic_ecommerce.historyfragment.data.entities.Order
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryFragmentViewModel (private val getOrdersByUserRepository: GetOrdersByUserRepository)
    : ViewModel() {
    var orders: MutableLiveData<List<Order>> = MutableLiveData()
    var onLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var onError: MutableLiveData<String> = MutableLiveData()
    fun getOrders(token: String) {
        onLoading.postValue(true)
        getOrdersByUserRepository.fetchGetOrdersByUser(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onLoading.postValue(false)
                if(it.code == 200)
                    orders.postValue(it.orders)
                else
                    onError.postValue("Could not get orders")
            }, {
                onLoading.postValue(false)
                onError.postValue(it.localizedMessage)
            })
    }
}