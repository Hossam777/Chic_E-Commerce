package com.app.chic_ecommerce.shopproductscategoryfragment.di

import com.app.chic_ecommerce.shopproductscategoryfragment.data.GetSubCategoriesAPI
import com.app.chic_ecommerce.shopproductscategoryfragment.data.GetSubCategoriesRepository
import com.app.chic_ecommerce.shopproductscategoryfragment.presentation.ShopProductsCategoryViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val shopProductsCategoryModule = Kodein.Module("ShopProductsCategoryModule"){
    bind<GetSubCategoriesAPI>() with singleton { GetSubCategoriesAPI() }
    bind<GetSubCategoriesRepository>() with singleton { GetSubCategoriesRepository(instance()) }
    bind<ShopProductsCategoryViewModel>() with provider { ShopProductsCategoryViewModel(instance()) }
}