package com.app.chic_ecommerce.common

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class BaseApplication : Application(), KodeinAware {
    override val kodein = Kodein{
        import(androidXModule(this@BaseApplication))
    }

}