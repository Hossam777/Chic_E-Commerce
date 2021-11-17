package com.app.chic_ecommerce.cartfragment.di

import com.app.chic_ecommerce.cartfragment.data.AddOrderAPI
import com.app.chic_ecommerce.cartfragment.data.AddOrderRepository
import com.app.chic_ecommerce.cartfragment.presentation.CartFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val cartFragmentModule = Kodein.Module("CartFragmentModule"){
    bind<AddOrderAPI>() with singleton { AddOrderAPI() }
    bind<AddOrderRepository>() with singleton { AddOrderRepository(instance()) }
    bind<CartFragmentViewModel>() with provider { CartFragmentViewModel(instance()) }
}