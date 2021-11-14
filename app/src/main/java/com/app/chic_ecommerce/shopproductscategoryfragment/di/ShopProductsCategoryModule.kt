package com.app.chic_ecommerce.shopproductscategoryfragment.di

import com.app.chic_ecommerce.shopproductscategoryfragment.presentation.ShopProductsCategoryViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val shopProductsCategoryModule = Kodein.Module("ShopProductsCategoryModule"){
    bind<ShopProductsCategoryViewModel>() with provider { ShopProductsCategoryViewModel() }
}