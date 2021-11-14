package com.app.chic_ecommerce.loginactivity.di

import com.app.chic_ecommerce.loginactivity.data.LoginAPI
import com.app.chic_ecommerce.loginactivity.data.LoginRepository
import com.app.chic_ecommerce.loginactivity.presentation.LoginActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val loginActivityModule = Kodein.Module("LoginActivityModule"){
    bind<LoginAPI>() with singleton { LoginAPI() }
    bind<LoginRepository>() with singleton { LoginRepository(instance()) }
    bind<LoginActivityViewModel>() with provider { LoginActivityViewModel(instance()) }
}