package com.app.chic_ecommerce.navigationactivity.di

import com.app.chic_ecommerce.navigationactivity.presentation.NavigationActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val navigationActivityModule = Kodein.Module("NavigationActivityModule"){
    bind<NavigationActivityViewModel>() with provider { NavigationActivityViewModel() }
}