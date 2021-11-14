package com.app.chic_ecommerce.shopproductsfragment.di

import com.app.chic_ecommerce.shopproductsfragment.data.GetCategoriesAPI
import com.app.chic_ecommerce.shopproductsfragment.data.GetCategoriesRepository
import com.app.chic_ecommerce.shopproductsfragment.presentation.ShopProductsFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val shopProductsFragmentModule = Kodein.Module("ShopProductsModule"){
    bind<GetCategoriesAPI>() with singleton { GetCategoriesAPI() }
    bind<GetCategoriesRepository>() with singleton { GetCategoriesRepository(instance()) }
    bind<ShopProductsFragmentViewModel>() with provider { ShopProductsFragmentViewModel(instance()) }
}