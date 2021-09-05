package com.app.chic_ecommerce.wishlistfragment.di

import com.app.chic_ecommerce.wishlistfragment.presentation.WishlistFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val wishlistFragmentModule = Kodein.Module("WishlistFragmentModule"){
    bind<WishlistFragmentViewModel>() with provider { WishlistFragmentViewModel() }
}