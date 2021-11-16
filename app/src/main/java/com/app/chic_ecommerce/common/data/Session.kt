package com.app.chic_ecommerce.common.data

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.app.chic_ecommerce.common.data.entities.CartProduct
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.common.data.entities.User

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
        if(wishlist.value!!.isEmpty()){
            sharedPreferences.writeString(activity, "wishlist", txt)
            return
        }
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
        if(cart.value!!.isEmpty()){
            sharedPreferences.writeString(activity, "cart", txt)
            return
        }
        for (p in cart.value!!.listIterator()){
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

    fun findInWishlist(product: Product): Int {
        var index = 0
        wishlist.value!!.forEach {
            if(it.id == product.id)
                return index
            index++
        }
        return -1
    }

    fun findInCart(product: CartProduct): Int {
        var index = 0
        cart.value!!.forEach {
            if(it.id == product.id)
                return index
            index++
        }
        return -1
    }

    fun addWishlistProduct(product: Product): Boolean {
        if(findInWishlist(product) != -1)
            return false
        val list = wishlist.value
        list!!.add(product)
        wishlist.postValue(list)
        saveWishlist()
        return true
    }

    fun removeWishlistProduct(product: Product): Boolean {
        val index = findInWishlist(product)
        if(index == -1)
            return false
        val list = wishlist.value
        list!!.removeAt(index)
        wishlist.postValue(list)
        saveWishlist()
        return true
    }

    fun addCartProduct(product: CartProduct): Boolean {
        val index = findInCart(product)
        val list = cart.value
        if(index != -1){
            val item = list!![index]
            list.removeAt(index)
            list.add(CartProduct(item.id, item.name, item.image, item.size, item.color, item.price, item.quantity + product.quantity))
            cart.postValue(list)
            return true
        }
        list!!.add(product)
        cart.postValue(list)
        return true
    }

    fun removeCartProduct(product: CartProduct): Boolean {
        val index = findInCart(product)
        if(index == -1)
            return false
        val list = cart.value
        list!!.removeAt(index)
        cart.postValue(list)
        return true
    }

    fun addCartProductQuantity(product: CartProduct): Boolean {
        val index = findInCart(product)
        if(index == -1)
            return false
        val list = cart.value
        val item = list!![index]
        list.removeAt(index)
        list.add(CartProduct(item.id, item.name, item.image, item.size, item.color, item.price, item.quantity + 1))
        cart.postValue(list)
        return true
    }

    fun removeCartProductQuantity(product: CartProduct): Boolean {
        val index = findInCart(product)
        if(index == -1)
            return false
        val list = cart.value
        val item = list!![index]
        list.removeAt(index)
        if (item.quantity != 1){
            list.add(CartProduct(item.id, item.name, item.image, item.size, item.color, item.price, item.quantity - 1))
        }
        cart.postValue(list)
        return true
    }
}