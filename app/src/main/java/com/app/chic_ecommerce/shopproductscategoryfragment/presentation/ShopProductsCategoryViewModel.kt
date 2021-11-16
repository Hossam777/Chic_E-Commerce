package com.app.chic_ecommerce.shopproductscategoryfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.shopproductscategoryfragment.data.GetProductsByFilterRepository
import com.app.chic_ecommerce.shopproductscategoryfragment.data.GetSubCategoriesRepository
import com.app.chic_ecommerce.shopproductscategoryfragment.data.entities.SubCategory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopProductsCategoryViewModel (
    private val getSubCategoriesRepository: GetSubCategoriesRepository,
    private val getProductsByFilterRepository: GetProductsByFilterRepository)
    : ViewModel() {
    var category: MutableLiveData<String> = MutableLiveData()
    var subCategories: MutableLiveData<MutableList<SubCategory>> = MutableLiveData()
    var products: MutableLiveData<MutableList<Product>> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()
    var selectedSubCategories: MutableLiveData<MutableList<SubCategory>> = MutableLiveData(mutableListOf())

    fun getSubCategories(){
        getSubCategoriesRepository.fetchGetCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                subCategories.postValue(it.message)
            }, {
                onError.postValue(it.localizedMessage)
            })
    }

    fun getProductsByFilter(){
        getProductsByFilterRepository.fetchGetProductsByFilter()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                products.postValue(it.products)
            }, {
                onError.postValue(it.localizedMessage)
            })
    }
}