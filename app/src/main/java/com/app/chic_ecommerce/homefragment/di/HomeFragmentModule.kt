package com.app.chic_ecommerce.homefragment.di

import com.app.chic_ecommerce.homefragment.presentation.HomeFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val homeFragmentModule = Kodein.Module("HomeFragmentModule"){
    bind<HomeFragmentViewModel>() with provider { HomeFragmentViewModel() }
}