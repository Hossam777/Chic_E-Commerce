package com.app.chic_ecommerce.common

import android.app.Application
import com.app.chic_ecommerce.common.di.commonModule
import com.app.chic_ecommerce.loginactivity.di.loginActivityModule
import com.app.chic_ecommerce.mainactivity.di.mainActivityModule
import com.app.chic_ecommerce.navigationactivity.di.navigationActivityModule
import com.app.chic_ecommerce.signupactivity.di.signupActivityModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class BaseApplication : Application(), KodeinAware {
    override val kodein = Kodein{
        import(androidXModule(this@BaseApplication))
        import(commonModule)
        import(mainActivityModule)
        import(loginActivityModule)
        import(signupActivityModule)
        import(navigationActivityModule)
    }

}