package com.app.chic_ecommerce.common.di

import com.app.chic_ecommerce.common.data.Session
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

var commonModule = Kodein.Module("CommonModule"){
    bind<Session>() with singleton { Session() }
}