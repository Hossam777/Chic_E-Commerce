package com.app.chic_ecommerce.common.data

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.app.chic_ecommerce.common.data.entities.CartProduct
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.common.data.entities.User
import com.app.chic_ecommerce.common.data.mockup.cartProducts

class Session {
    private lateinit var activity: Activity
    private var sharedPreferences: SharedPreferences = SharedPreferences()
    var token: MutableLiveData<String> = MutableLiveData()
    var user: MutableLiveData<User> = MutableLiveData()
    var wishlist: MutableLiveData<MutableList<Product>> = MutableLiveData(arrayListOf())
    var cart: MutableLiveData<MutableList<CartProduct>> = MutableLiveData(arrayListOf())
    var currentFragment: MutableLiveData<FragmentsEnum> = MutableLiveData(FragmentsEnum.HomeFragment)
    var focusedProduct: MutableLiveData<Product> = MutableLiveData()

    fun setActivity(activity: Activity){
        this.activity = activity
    }

    fun saveToken (token: String){
        sharedPreferences.writeString(activity, "token", token)
        println("Saving....")
    }

    fun loadToken (){
        token.postValue(this.sharedPreferences.readString(activity, "token"))
    }

    fun saveWishlist (){
        var txt = ""
        for (p in wishlist.value!!.listIterator()){
            txt += "$p|"
        }
        txt = txt.subSequence(0, txt.length - 1) as String
        sharedPreferences.writeString(activity, "wishlist", txt)
    }

    fun loadWishlist (){
        val list: MutableList<Product> = mutableListOf()
        val txt = sharedPreferences.readString(activity, "wishlist")
        if(txt != ""){
            txt.split("|").listIterator().forEach{
                list.add(Product(it.split(",")))
            }
            wishlist.postValue(list)
        }
    }

    fun saveCart (){
        var txt = ""
        for (p in cartProducts.listIterator()){
            txt += "$p|"
        }
        txt = txt.subSequence(0, txt.length - 1) as String
        sharedPreferences.writeString(activity, "cart", txt)
    }

    fun loadCart (){
        val list: MutableList<CartProduct> = mutableListOf()
        val txt = sharedPreferences.readString(activity, "cart")
        if (txt != ""){
            txt.split("|").listIterator().forEach{
                list.add(CartProduct(it.split(",")))
            }
            cart.postValue(list)
        }
    }
}