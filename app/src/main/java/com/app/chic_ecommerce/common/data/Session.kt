package com.app.chic_ecommerce.common.data

import androidx.lifecycle.MutableLiveData
import com.app.chic_ecommerce.common.data.entities.User

class Session {
    var user: MutableLiveData<User> = MutableLiveData()
}