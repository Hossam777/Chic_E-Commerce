package com.app.chic_ecommerce.historyfragment.di

import com.app.chic_ecommerce.historyfragment.data.GetOrdersByUserAPI
import com.app.chic_ecommerce.historyfragment.data.GetOrdersByUserRepository
import com.app.chic_ecommerce.historyfragment.presentation.HistoryFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val historyFragmentModule = Kodein.Module("HistoryFragmentModule"){
    bind<GetOrdersByUserAPI>() with singleton { GetOrdersByUserAPI() }
    bind<GetOrdersByUserRepository>() with singleton { GetOrdersByUserRepository(instance()) }
    bind<HistoryFragmentViewModel>() with provider { HistoryFragmentViewModel(instance()) }
}