package com.app.chic_ecommerce.loginactivity.di

import com.app.chic_ecommerce.loginactivity.presentation.LoginActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val loginActivityModule = Kodein.Module("LoginActivityModule"){
    bind<LoginActivityViewModel>() with provider { LoginActivityViewModel() }
}