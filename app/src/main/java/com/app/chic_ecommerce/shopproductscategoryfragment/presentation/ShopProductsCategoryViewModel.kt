package com.app.chic_ecommerce.shopproductscategoryfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopProductsCategoryViewModel: ViewModel() {
    var category: MutableLiveData<String> = MutableLiveData()
}