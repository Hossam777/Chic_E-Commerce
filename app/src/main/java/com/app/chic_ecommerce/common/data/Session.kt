package com.app.chic_ecommerce.common.data

import androidx.lifecycle.MutableLiveData
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.common.data.entities.User

class Session {
    var user: MutableLiveData<User> = MutableLiveData()
    var wishlist: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    var cart: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    var currentFragment: MutableLiveData<FragmentsEnum> = MutableLiveData()
}