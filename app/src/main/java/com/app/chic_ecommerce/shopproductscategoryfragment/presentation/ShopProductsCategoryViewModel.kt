package com.app.chic_ecommerce.shopproductscategoryfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.shopproductscategoryfragment.data.GetSubCategoriesRepository
import com.app.chic_ecommerce.shopproductscategoryfragment.data.entities.SubCategory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopProductsCategoryViewModel (private val getSubCategoriesRepository: GetSubCategoriesRepository)
    : ViewModel() {
    var category: MutableLiveData<String> = MutableLiveData()
    var subCategory: MutableLiveData<MutableList<SubCategory>> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()
    fun getSubCategories(){
        getSubCategoriesRepository.fetchGetCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                subCategory.postValue(it.message)
            }, {
                onError.postValue(it.localizedMessage)
            })
    }
}