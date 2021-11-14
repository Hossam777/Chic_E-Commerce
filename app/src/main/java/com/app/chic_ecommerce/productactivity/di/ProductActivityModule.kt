package com.app.chic_ecommerce.productactivity.di

import com.app.chic_ecommerce.productactivity.presentation.ProductActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val productActivityModule = Kodein.Module("ProductActivityModule"){
    bind<ProductActivityViewModel>() with provider { ProductActivityViewModel() }
}