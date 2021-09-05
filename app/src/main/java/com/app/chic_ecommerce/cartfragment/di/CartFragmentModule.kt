package com.app.chic_ecommerce.cartfragment.di

import com.app.chic_ecommerce.cartfragment.presentation.CartFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val cartFragmentModule = Kodein.Module("CartFragmentModule"){
    bind<CartFragmentViewModel>() with provider { CartFragmentViewModel() }
}