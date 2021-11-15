package com.app.chic_ecommerce.productactivity.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductActivityViewModel: ViewModel() {
    var selectedColor: MutableLiveData<String> = MutableLiveData()
}