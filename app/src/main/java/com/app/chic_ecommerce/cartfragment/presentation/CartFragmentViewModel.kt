package com.app.chic_ecommerce.cartfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartFragmentViewModel: ViewModel() {
    var promoCode: MutableLiveData<String> = MutableLiveData("")
}