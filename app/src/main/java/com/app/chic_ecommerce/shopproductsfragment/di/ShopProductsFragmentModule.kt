package com.app.chic_ecommerce.shopproductsfragment.di

import com.app.chic_ecommerce.shopproductsfragment.presentation.ShopProductsFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val shopProductsFragmentModule = Kodein.Module("ShopProductsModule"){
    bind<ShopProductsFragmentViewModel>() with provider { ShopProductsFragmentViewModel() }
}