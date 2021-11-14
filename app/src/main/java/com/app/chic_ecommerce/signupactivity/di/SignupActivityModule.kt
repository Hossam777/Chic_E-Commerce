package com.app.chic_ecommerce.signupactivity.di

import com.app.chic_ecommerce.signupactivity.data.SignupAPI
import com.app.chic_ecommerce.signupactivity.data.SignupRepository
import com.app.chic_ecommerce.signupactivity.presentation.SignupActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val signupActivityModule = Kodein.Module("SignupActivityModule"){
    bind<SignupAPI>() with singleton { SignupAPI() }
    bind<SignupRepository>() with singleton { SignupRepository(instance()) }
    bind<SignupActivityViewModel>() with provider { SignupActivityViewModel(instance()) }
}