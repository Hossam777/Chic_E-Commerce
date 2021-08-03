package com.app.chic_ecommerce.mainactivity.di

import com.app.chic_ecommerce.mainactivity.presentation.MainActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val mainActivityModule = Kodein.Module("MainActivityModule"){
    bind<MainActivityViewModel>() with provider { MainActivityViewModel() }
}