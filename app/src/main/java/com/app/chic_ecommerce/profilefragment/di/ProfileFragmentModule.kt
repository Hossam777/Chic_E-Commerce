package com.app.chic_ecommerce.profilefragment.di

import com.app.chic_ecommerce.profilefragment.presentation.ProfileFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val profileFragmentModule = Kodein.Module("ProfileFragmentModule"){
    bind<ProfileFragmentViewModel>() with provider { ProfileFragmentViewModel() }
}