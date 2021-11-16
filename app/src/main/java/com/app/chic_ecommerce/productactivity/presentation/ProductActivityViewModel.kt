package com.app.chic_ecommerce.productactivity.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.common.data.entities.Color

class ProductActivityViewModel: ViewModel() {
    var selectedColor: MutableLiveData<Color> = MutableLiveData()
}