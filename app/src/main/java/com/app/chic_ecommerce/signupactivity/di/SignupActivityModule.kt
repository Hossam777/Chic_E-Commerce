package com.app.chic_ecommerce.signupactivity.di

import com.app.chic_ecommerce.signupactivity.presentation.SignupActivityViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val signupActivityModule = Kodein.Module("SignupActivityModule"){
    bind<SignupActivityViewModel>() with provider { SignupActivityViewModel() }
}