package com.app.chic_ecommerce.shopproductsfragment.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chic_ecommerce.shopproductsfragment.data.GetCategoriesRepository
import com.app.chic_ecommerce.shopproductsfragment.data.entities.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopProductsFragmentViewModel (private val getCategoriesRepository: GetCategoriesRepository)
    : ViewModel() {
    var categories: MutableLiveData<MutableList<Category>> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()
    fun getCategories(){
        getCategoriesRepository.fetchGetCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                categories.postValue(it.categories)
            }, {
                onError.postValue(it.localizedMessage)
            })
    }
}