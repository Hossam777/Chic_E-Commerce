package com.app.chic_ecommerce.mainactivity.di

import com.app.chic_ecommerce.mainactivity.data.CheckTokenAPI
import com.app.chic_ecommerce.mainactivity.data.CheckTokenRepository
import com.app.chic_ecommerce.mainactivity.presentation.MainActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val mainActivityModule = Kodein.Module("MainActivityModule"){
    bind<CheckTokenAPI>() with singleton { CheckTokenAPI() }
    bind<CheckTokenRepository>() with singleton { CheckTokenRepository(instance()) }
    bind<MainActivityViewModel>() with provider { MainActivityViewModel(instance()) }
}